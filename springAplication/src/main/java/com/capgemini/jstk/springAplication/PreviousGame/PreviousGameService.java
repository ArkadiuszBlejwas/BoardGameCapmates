package com.capgemini.jstk.springAplication.PreviousGame;

import com.capgemini.jstk.springAplication.Interfaces.PreviousGameRepository;
import com.capgemini.jstk.springAplication.User.UserDTO;
import com.capgemini.jstk.springAplication.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreviousGameService {

    private PreviousGameRepository previousGameRepository;
    private PreviousGameMapper previousGameMapper;
    private UserService userService;

    @Autowired
    public PreviousGameService(PreviousGameRepository previousGameRepository,
                               PreviousGameMapper previousGameMapper,
                               UserService userService){
        this.previousGameRepository = previousGameRepository;
        this.previousGameMapper = previousGameMapper;
        this.userService = userService;
    }

    public List<PreviousGameDTO> getPreviousGamesByIdUser(Long idUser){
        UserDTO user = userService.getUserById(idUser);
        return user.getPreviousGames();
    }

    public PreviousGameDTO getPrevGameById(Long idPrev){
        return previousGameMapper.convertToDto(previousGameRepository.get(idPrev));
    }

    public void addPreviousGameToSystem(PreviousGameDTO previousGameDTO){
        previousGameRepository.add(previousGameMapper.convertToEntity(previousGameDTO));
    }

    public void removePreviousGameFromSystem(Long idPrev){
        PreviousGameEntity prevGame = previousGameMapper.convertToEntity(getPrevGameById(idPrev));
        previousGameRepository.remove(prevGame);
    }

    public void updatePreviousGameInSystem(PreviousGameDTO previousGameDTO){
        PreviousGameEntity prevGame = previousGameMapper.convertToEntity(previousGameDTO);
        previousGameRepository.update(prevGame);
    }

    public void callSetup(){
        previousGameRepository.setupPreviousGameDAO();
    }
}
