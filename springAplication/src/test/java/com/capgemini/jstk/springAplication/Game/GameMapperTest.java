package com.capgemini.jstk.springAplication.Game;

import com.capgemini.jstk.springAplication.Aplication.SpringAplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAplication.class)
public class GameMapperTest {

    @Autowired
    GameMapper gameMapper;

    private Set<GameEntity> setGameEntitySet(){
        Set<GameEntity> gameEntitySet = new HashSet<>();
        gameEntitySet.add(new GameEntity( 0L, "Domino", 2, 2));
        gameEntitySet.add(new GameEntity( 1L, "Warcaby", 2, 2));
        gameEntitySet.add(new GameEntity( 2L, "Szachy", 2, 2));
        gameEntitySet.add(new GameEntity( 3L, "Chińczyk", 2, 4));
        gameEntitySet.add(new GameEntity( 4L, "Monopoly", 2, 8));
        gameEntitySet.add(new GameEntity( 5L, "Shogi", 2, 2));
        return gameEntitySet;
    }

    private Set<GameDTO> setGameDTOSet(){
        Set<GameDTO> gameDTOSet = new HashSet<>();
        gameDTOSet.add(new GameDTO( 0L, "Domino", 2, 2));
        gameDTOSet.add(new GameDTO( 1L, "Warcaby", 2, 2));
        gameDTOSet.add(new GameDTO( 2L, "Szachy", 2, 2));
        gameDTOSet.add(new GameDTO( 3L, "Chińczyk", 2, 4));
        gameDTOSet.add(new GameDTO( 4L, "Monopoly", 2, 8));
        gameDTOSet.add(new GameDTO( 5L, "Shogi", 2, 2));
        return gameDTOSet;
    }

    private List<GameEntity> setGameEntityList(){
        List<GameEntity> gameEntityList = new ArrayList<>();
        gameEntityList.add(new GameEntity( 0L, "Domino", 2, 2));
        gameEntityList.add(new GameEntity( 1L, "Warcaby", 2, 2));
        gameEntityList.add(new GameEntity( 2L, "Szachy", 2, 2));
        gameEntityList.add(new GameEntity( 3L, "Chińczyk", 2, 4));
        gameEntityList.add(new GameEntity( 4L, "Monopoly", 2, 8));
        gameEntityList.add(new GameEntity( 5L, "Shogi", 2, 2));
        return gameEntityList;
    }

    private List<GameDTO> setGameDTOList(){
        List<GameDTO> gameDTOList = new ArrayList<>();
        gameDTOList.add(new GameDTO( 0L, "Domino", 2, 2));
        gameDTOList.add(new GameDTO( 1L, "Warcaby", 2, 2));
        gameDTOList.add(new GameDTO( 2L, "Szachy", 2, 2));
        gameDTOList.add(new GameDTO( 3L, "Chińczyk", 2, 4));
        gameDTOList.add(new GameDTO( 4L, "Monopoly", 2, 8));
        gameDTOList.add(new GameDTO( 5L, "Shogi", 2, 2));
        return gameDTOList;
    }

    @Test
    public void convertToDto() {
        GameDTO exceptedGame = setGameDTOList().get(0);
        GameEntity gameEntity = setGameEntityList().get(0);
        assertEquals(exceptedGame, gameMapper.convertToDto(gameEntity));
    }

    @Test
    public void convertToEntity() {
        GameEntity exceptedGame = setGameEntityList().get(0);
        GameDTO gameDTO = setGameDTOList().get(0);
        assertEquals(exceptedGame, gameMapper.convertToEntity(gameDTO));
    }

    @Test
    public void convertToDtoList() {
        List<GameDTO> exceptedList = setGameDTOList();
        List<GameEntity> gameEntityList = setGameEntityList();
        assertEquals(exceptedList, gameMapper.convertToDtoList(gameEntityList));
    }

    @Test
    public void convertToEntityList() {
        List<GameEntity> exceptedList = setGameEntityList();
        List<GameDTO> gameDTOList = setGameDTOList();
        assertEquals(exceptedList, gameMapper.convertToEntityList(gameDTOList));
    }

    @Test
    public void convertToDtoSet() {
        Set<GameDTO> esceptedSet = setGameDTOSet();
        Set<GameEntity> gameEntitySet = setGameEntitySet();
        assertEquals(esceptedSet, gameMapper.convertToDtoSet(gameEntitySet));
    }

    @Test
    public void convertToEntitySet() {
        Set<GameEntity> exceptedSet = setGameEntitySet();
        Set<GameDTO> gameDTOSet = setGameDTOSet();
        assertEquals(exceptedSet, gameMapper.convertToEntitySet(gameDTOSet));
    }
}