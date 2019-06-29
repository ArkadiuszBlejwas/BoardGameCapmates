package com.capgemini.jstk.springAplication.Game;

import com.capgemini.jstk.springAplication.Interfaces.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GameMapper implements Mapper<GameEntity, GameDTO> {

    public GameDTO convertToDto(GameEntity gameEntity){
        if (gameEntity == null){
            return null;
        }
        return new GameDTO(
                gameEntity.getIdGame(),
                gameEntity.getNameGame(),
                gameEntity.getMinNumberOfPlayers(),
                gameEntity.getMaxNumberOfPlayers());
    }

    public GameEntity convertToEntity(GameDTO gameDTO){
        if (gameDTO == null){
            return null;
        }
        return new GameEntity(
                gameDTO.getIdGame(),
                gameDTO.getNameGame(),
                gameDTO.getMinNumberOfPlayers(),
                gameDTO.getMaxNumberOfPlayers());
    }

    public List<GameDTO> convertToDtoList(List<GameEntity> gameEntityList){

        return gameEntityList.stream()
                .map(Game -> new GameDTO(
                        Game.getIdGame(),
                        Game.getNameGame(),
                        Game.getMinNumberOfPlayers(),
                        Game.getMaxNumberOfPlayers()))
                .collect(Collectors.toList());
    }

    public List<GameEntity> convertToEntityList(List<GameDTO> gameDTOList){
        return gameDTOList.stream()
                .map(Game -> new GameEntity(
                        Game.getIdGame(),
                        Game.getNameGame(),
                        Game.getMinNumberOfPlayers(),
                        Game.getMaxNumberOfPlayers()))
                .collect(Collectors.toList());
    }

    public Set<GameDTO> convertToDtoSet(Set<GameEntity> gameEntitySet){
        return gameEntitySet.stream()
                .map(Game -> new GameDTO(
                        Game.getIdGame(),
                        Game.getNameGame(),
                        Game.getMinNumberOfPlayers(),
                        Game.getMaxNumberOfPlayers()))
                .collect(Collectors.toSet());
    }

    public Set<GameEntity> convertToEntitySet(Set<GameDTO> gameDTOSet){
        return gameDTOSet.stream()
                .map(Game -> new GameEntity(
                        Game.getIdGame(),
                        Game.getNameGame(),
                        Game.getMinNumberOfPlayers(),
                        Game.getMaxNumberOfPlayers()))
                .collect(Collectors.toSet());
    }
}
