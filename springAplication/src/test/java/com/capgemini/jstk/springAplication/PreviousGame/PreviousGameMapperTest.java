package com.capgemini.jstk.springAplication.PreviousGame;

import com.capgemini.jstk.springAplication.Aplication.SpringAplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAplication.class)
public class PreviousGameMapperTest {

    @Autowired
    PreviousGameMapper previousGameMapper;

    private List<PreviousGameDTO> setPreviousGameDTOList(){
        List<PreviousGameDTO> previousGameDTOList = new ArrayList<>();
        previousGameDTOList.add(new PreviousGameDTO(0L,0L, LocalDateTime.of(2009, Month.FEBRUARY, 20, 16, 35), Arrays.asList(2L, 1L), true));
        previousGameDTOList.add(new PreviousGameDTO(1L,0L, LocalDateTime.of(2008, Month.FEBRUARY, 20, 16, 35), Arrays.asList(2L, 1L), true));
        previousGameDTOList.add(new PreviousGameDTO(2L,0L, LocalDateTime.of(2007, Month.FEBRUARY, 20, 16, 35), Arrays.asList(2L, 1L), true));
        previousGameDTOList.add(new PreviousGameDTO(3L,0L, LocalDateTime.of(2006, Month.FEBRUARY, 20, 16, 35), Arrays.asList(2L, 1L), true));
        previousGameDTOList.add(new PreviousGameDTO(4L,0L, LocalDateTime.of(2005, Month.FEBRUARY, 20, 16, 35), Arrays.asList(2L, 1L), true));
        return previousGameDTOList;
    }

    private List<PreviousGameEntity> setPreviousGameEntityList(){
        List<PreviousGameEntity> previousGameEntityList = new ArrayList<>();
        previousGameEntityList.add(new PreviousGameEntity(0L,0L, LocalDateTime.of(2009, Month.FEBRUARY, 20, 16, 35), Arrays.asList(2L, 1L), true));
        previousGameEntityList.add(new PreviousGameEntity(1L,0L, LocalDateTime.of(2008, Month.FEBRUARY, 20, 16, 35), Arrays.asList(2L, 1L), true));
        previousGameEntityList.add(new PreviousGameEntity(2L,0L, LocalDateTime.of(2007, Month.FEBRUARY, 20, 16, 35), Arrays.asList(2L, 1L), true));
        previousGameEntityList.add(new PreviousGameEntity(3L,0L, LocalDateTime.of(2006, Month.FEBRUARY, 20, 16, 35), Arrays.asList(2L, 1L), true));
        previousGameEntityList.add(new PreviousGameEntity(4L,0L, LocalDateTime.of(2005, Month.FEBRUARY, 20, 16, 35), Arrays.asList(2L, 1L), true));
        return previousGameEntityList;
    }

    @Test
    public void convertToDto() {
        PreviousGameDTO exceptedPrevGame = setPreviousGameDTOList().get(0);
        PreviousGameEntity prevGameEntity = setPreviousGameEntityList().get(0);
        assertEquals(exceptedPrevGame, previousGameMapper.convertToDto(prevGameEntity));
    }

    @Test
    public void convertToEntity() {
        PreviousGameEntity exceptedPrevGame = setPreviousGameEntityList().get(0);
        PreviousGameDTO prevGameDTO = setPreviousGameDTOList().get(0);
        assertEquals(exceptedPrevGame, previousGameMapper.convertToEntity(prevGameDTO));
    }

    @Test
    public void convertToDtoList() {
        List<PreviousGameDTO> exceptedList = setPreviousGameDTOList();
        List<PreviousGameEntity> prevGameEntityList = setPreviousGameEntityList();
        assertEquals(exceptedList, previousGameMapper.convertToDtoList(prevGameEntityList));
    }

    @Test
    public void convertToEntityList() {
        List<PreviousGameEntity> exceptedList = setPreviousGameEntityList();
        List<PreviousGameDTO> prevGameDTOList = setPreviousGameDTOList();
        assertEquals(exceptedList, previousGameMapper.convertToEntityList(prevGameDTOList));
    }
}