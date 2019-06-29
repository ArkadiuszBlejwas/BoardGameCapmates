package com.capgemini.jstk.springAplication.Game;

import com.capgemini.jstk.springAplication.AvailabilityTime.AvailabilityTimeDTO;
import com.capgemini.jstk.springAplication.Interfaces.AvailabilityTimeRepository;
import com.capgemini.jstk.springAplication.Interfaces.GameRepository;
import com.capgemini.jstk.springAplication.User.UserDTO;
import com.capgemini.jstk.springAplication.User.UserEntity;
import com.capgemini.jstk.springAplication.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GameService {

    private GameRepository gameRepository;
    private GameMapper gameMapper;
    private UserService userService;

    @Autowired
    public GameService(GameRepository gameRepository, GameMapper gameMapper, UserService userService){
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.userService = userService;
    }

    public GameDTO getGameById(Long id){
        return gameMapper.convertToDto(gameRepository.get(id));
    }

    public List<GameDTO> getGamesFromSystem(){
        List<GameEntity> games = gameRepository.getAll();
        return gameMapper.convertToDtoList(games);
    }

    public Set<GameDTO> getUserGames(Long idUser){
        return userService.getUserById(idUser).getMyGames();
    }

    public void addGameToSystem(Long idUser, GameDTO gameDTO){
        if (getGamesFromSystem().contains(gameDTO)){
            throw new IllegalArgumentException("This game already exists in system");
        }
        gameRepository.add(gameMapper.convertToEntity(gameDTO));
        addGameToMyCollectionFromSystem(idUser, gameDTO.getIdGame());
    }

    public void addGameToMyCollectionFromSystem(Long idUser, Long idGame){
        UserDTO user = userService.getUserById(idUser);
        GameDTO game = getGameById(idGame);
        Set<GameDTO> gameSet = user.getMyGames();
        if (!gameSet.add(game)){
            throw new IllegalArgumentException("This game already exists in my collection");
        }
        setGamesInUserEntity(gameSet, user);
    }

    public void removeGameFromMyCollection(Long idUser, Long idGame){
        UserDTO user = userService.getUserById(idUser);
        GameDTO game = getGameById(idGame);
        Set<GameDTO> gameSet = user.getMyGames();
        if (!gameSet.remove(game)){
            throw new IllegalArgumentException("This element does not exist in your collection");
        }
        setGamesInUserEntity(gameSet, user);
        gameRepository.remove(gameMapper.convertToEntity(game));
    }

    private void setGamesInUserEntity(Set<GameDTO> gameSet, UserDTO user){
        user.setMyGames(gameSet);
        UserEntity userEntity = userService.getUserMapper().convertToEntity(user);
        userService.getUserRepository().update(userEntity);
    }


    public void callSetup(){
        gameRepository.setupGameDAO();
    }
}
