package com.capgemini.jstk.springAplication.PreviousGame;

import com.capgemini.jstk.springAplication.Interfaces.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PreviousGameMapper implements Mapper<PreviousGameEntity, PreviousGameDTO> {

    public PreviousGameDTO convertToDto(PreviousGameEntity previousGameEntity){
        if (previousGameEntity == null){
            return null;
        }
        return new PreviousGameDTO(
                previousGameEntity.getIdPreviousGame(),
                previousGameEntity.getIdGame(),
                previousGameEntity.getTimeGame(),
                previousGameEntity.getIdPlayers(),
                previousGameEntity.isWinner());
    }

    public PreviousGameEntity convertToEntity(PreviousGameDTO previousGameDTO){
        if (previousGameDTO == null){
            return null;
        }
        return new PreviousGameEntity(
                previousGameDTO.getIdPreviousGame(),
                previousGameDTO.getIdGame(),
                previousGameDTO.getTimeGame(),
                previousGameDTO.getIdPlayers(),
                previousGameDTO.isWinner());
    }

    public List<PreviousGameDTO> convertToDtoList (List<PreviousGameEntity> previousGameEntityList){
        return previousGameEntityList.stream()
                .map(game -> new PreviousGameDTO(
                        game.getIdPreviousGame(),
                        game.getIdGame(),
                        game.getTimeGame(),
                        game.getIdPlayers(),
                        game.isWinner()))
                .collect(Collectors.toList());
    }

    public List<PreviousGameEntity> convertToEntityList (List<PreviousGameDTO> previousGameDTOList){
        return previousGameDTOList.stream()
                .map(game -> new PreviousGameEntity(
                        game.getIdPreviousGame(),
                        game.getIdGame(),
                        game.getTimeGame(),
                        game.getIdPlayers(),
                        game.isWinner()))
                .collect(Collectors.toList());
    }
}
