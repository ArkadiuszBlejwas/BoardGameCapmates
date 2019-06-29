package com.capgemini.jstk.springAplication.User;

import com.capgemini.jstk.springAplication.AvailabilityTime.AvailabilityTimeDTO;
import com.capgemini.jstk.springAplication.AvailabilityTime.AvailabilityTimeEntity;
import com.capgemini.jstk.springAplication.AvailabilityTime.AvailabilityTimeMapper;
import com.capgemini.jstk.springAplication.Game.GameDTO;
import com.capgemini.jstk.springAplication.Game.GameEntity;
import com.capgemini.jstk.springAplication.Game.GameMapper;
import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameDTO;
import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameEntity;
import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    private UserMapper userMapper;

    @Mock
    private GameMapper gameMapper;

    @Mock
    private PreviousGameMapper previousGameMapper;

    @Mock
    private AvailabilityTimeMapper availabilityTimeMapper;

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

    private List<PreviousGameEntity> setPreviousGameEntityList(){
        List<PreviousGameEntity> previousGameEntityList = new ArrayList<>();
        previousGameEntityList.add(new PreviousGameEntity(0L,0L, LocalDateTime.of(2009, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true));
        previousGameEntityList.add(new PreviousGameEntity(1L,0L, LocalDateTime.of(2008, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true));
        previousGameEntityList.add(new PreviousGameEntity(2L,0L, LocalDateTime.of(2007, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true));
        previousGameEntityList.add(new PreviousGameEntity(3L,0L, LocalDateTime.of(2006, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true));
        previousGameEntityList.add(new PreviousGameEntity(4L,0L, LocalDateTime.of(2005, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true));
        return previousGameEntityList;
    }

    private List<PreviousGameDTO> setPreviousGameDTOList(){
        List<PreviousGameDTO> previousGameDTOList = new ArrayList<>();
        previousGameDTOList.add(new PreviousGameDTO(0L,0L, LocalDateTime.of(2009, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true));
        previousGameDTOList.add(new PreviousGameDTO(1L,0L, LocalDateTime.of(2008, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true));
        previousGameDTOList.add(new PreviousGameDTO(2L,0L, LocalDateTime.of(2007, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true));
        previousGameDTOList.add(new PreviousGameDTO(3L,0L, LocalDateTime.of(2006, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true));
        previousGameDTOList.add(new PreviousGameDTO(4L,0L, LocalDateTime.of(2005, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true));
        return previousGameDTOList;
    }

    private List<AvailabilityTimeEntity> setAvailabilityTimeEntityList(){
        List<AvailabilityTimeEntity> availabilityTimeEntityList = new ArrayList<>();
        availabilityTimeEntityList.add(new AvailabilityTimeEntity(0L, LocalDateTime.of(2010, Month.MARCH, 15, 15, 30), LocalDateTime.of(2010, Month.MARCH, 15, 16, 30), true, "Mój komentarz"));
        availabilityTimeEntityList.add(new AvailabilityTimeEntity(1L, LocalDateTime.of(2011, Month.MARCH, 15, 15, 30), LocalDateTime.of(2011, Month.MARCH, 15, 16, 30), true, "Mój komentarz"));
        availabilityTimeEntityList.add(new AvailabilityTimeEntity(2L, LocalDateTime.of(2012, Month.MARCH, 15, 15, 30), LocalDateTime.of(2012, Month.MARCH, 15, 16, 30), true, "Mój komentarz"));
        availabilityTimeEntityList.add(new AvailabilityTimeEntity(3L, LocalDateTime.of(2013, Month.MARCH, 15, 15, 30), LocalDateTime.of(2013, Month.MARCH, 15, 16, 30), true, "Mój komentarz"));
        availabilityTimeEntityList.add(new AvailabilityTimeEntity(4L, LocalDateTime.of(2014, Month.MARCH, 15, 15, 30), LocalDateTime.of(2014, Month.MARCH, 15, 16, 30), true, "Mój komentarz"));
        return availabilityTimeEntityList;
    }

    private List<AvailabilityTimeDTO> setAvailabilityTimeDTOList(){
        List<AvailabilityTimeDTO> availabilityTimeDTOList = new ArrayList<>();
        availabilityTimeDTOList.add(new AvailabilityTimeDTO(0L, LocalDateTime.of(2010, Month.MARCH, 15, 15, 30), LocalDateTime.of(2010, Month.MARCH, 15, 16, 30), true, "Mój komentarz"));
        availabilityTimeDTOList.add(new AvailabilityTimeDTO(1L, LocalDateTime.of(2011, Month.MARCH, 15, 15, 30), LocalDateTime.of(2011, Month.MARCH, 15, 16, 30), true, "Mój komentarz"));
        availabilityTimeDTOList.add(new AvailabilityTimeDTO(2L, LocalDateTime.of(2012, Month.MARCH, 15, 15, 30), LocalDateTime.of(2012, Month.MARCH, 15, 16, 30), true, "Mój komentarz"));
        availabilityTimeDTOList.add(new AvailabilityTimeDTO(3L, LocalDateTime.of(2013, Month.MARCH, 15, 15, 30), LocalDateTime.of(2013, Month.MARCH, 15, 16, 30), true, "Mój komentarz"));
        availabilityTimeDTOList.add(new AvailabilityTimeDTO(4L, LocalDateTime.of(2014, Month.MARCH, 15, 15, 30), LocalDateTime.of(2014, Month.MARCH, 15, 16, 30), true, "Mój komentarz"));
        return availabilityTimeDTOList;
    }

    @Before
    public void setup(){
        userMapper = new UserMapper(gameMapper, previousGameMapper, availabilityTimeMapper);
        Set<GameEntity> gameEntitySet = setGameEntitySet();

        Set<GameDTO> gameDTOSet = setGameDTOSet();

        when(gameMapper.convertToDtoSet(gameEntitySet)).thenReturn(gameDTOSet);
        when(gameMapper.convertToEntitySet(gameDTOSet)).thenReturn(gameEntitySet);

        List<PreviousGameEntity> previousGameEntityList = setPreviousGameEntityList();
        List<PreviousGameDTO> previousGameDTOList = setPreviousGameDTOList();

        when(previousGameMapper.convertToDtoList(previousGameEntityList)).thenReturn(previousGameDTOList);
        when(previousGameMapper.convertToEntityList(previousGameDTOList)).thenReturn(previousGameEntityList);

        List<AvailabilityTimeEntity> availabilityTimeEntityList = setAvailabilityTimeEntityList();
        List<AvailabilityTimeDTO> availabilityTimeDTOList = setAvailabilityTimeDTOList();

        when(availabilityTimeMapper.convertToDtoList(availabilityTimeEntityList)).thenReturn(availabilityTimeDTOList);
        when(availabilityTimeMapper.convertToEntityList(availabilityTimeDTOList)).thenReturn(availabilityTimeEntityList);

    }

    @Test
    public void convertToDto() {
        UserDTO exceptedUser = new UserDTO(
                0L,
                "Arek",
                "Nowak",
                "arek@wp.pl",
                "lalala",
                "Aequitas sequitur legem",
                setGameDTOSet(),
                setPreviousGameDTOList(),
                setAvailabilityTimeDTOList());
        UserEntity userEntity = new UserEntity(
                0L,
                "Arek",
                "Nowak",
                "arek@wp.pl",
                "lalala",
                "Aequitas sequitur legem",
                setGameEntitySet(),
                setPreviousGameEntityList(),
                setAvailabilityTimeEntityList());
        assertEquals(exceptedUser, userMapper.convertToDto(userEntity));
    }

    @Test
    public void convertToEntity() {
        UserEntity exceptedUser = new UserEntity(
                0L,
                "Arek",
                "Nowak",
                "arek@wp.pl",
                "lalala",
                "Aequitas sequitur legem",
                setGameEntitySet(),
                setPreviousGameEntityList(),
                setAvailabilityTimeEntityList());
        UserDTO userDTO = new UserDTO(
                0L,
                "Arek",
                "Nowak",
                "arek@wp.pl",
                "lalala",
                "Aequitas sequitur legem",
                setGameDTOSet(),
                setPreviousGameDTOList(),
                setAvailabilityTimeDTOList());
        assertEquals(exceptedUser, userMapper.convertToEntity(userDTO));
    }

    @Test
    public void convertToDtoList() {
        List<UserDTO> exceptedList = new ArrayList<>();
        exceptedList.add(new UserDTO(0L,
                "Arkadiusz",
                "Blejwas",
                "gothicarka@wp.pl",
                "lolek55",
                "xD",
                setGameDTOSet(),
                setPreviousGameDTOList(),
                setAvailabilityTimeDTOList()));
        exceptedList.add(new UserDTO(1L,
                "Maciej",
                "Kowalski",
                "mackokowalski99@wp.pl",
                "mackomacko11",
                "carpe diem",
                setGameDTOSet(),
                setPreviousGameDTOList(),
                setAvailabilityTimeDTOList()));
        exceptedList.add(new UserDTO(2L,
                "Aleksandra",
                "Nowak",
                "olanowak99@wp.pl",
                "asdfasdf77",
                "habemus papam",
                setGameDTOSet(),
                setPreviousGameDTOList(),
                setAvailabilityTimeDTOList()));

        List<UserEntity> userEntityList = new ArrayList<>();
        userEntityList.add(new UserEntity(0L,
                "Arkadiusz",
                "Blejwas",
                "gothicarka@wp.pl",
                "lolek55",
                "xD",
                setGameEntitySet(),
                setPreviousGameEntityList(),
                setAvailabilityTimeEntityList()));
        userEntityList.add(new UserEntity(1L,
                "Maciej",
                "Kowalski",
                "mackokowalski99@wp.pl",
                "mackomacko11",
                "carpe diem",
                setGameEntitySet(),
                setPreviousGameEntityList(),
                setAvailabilityTimeEntityList()));
        userEntityList.add(new UserEntity(2L,
                "Aleksandra",
                "Nowak",
                "olanowak99@wp.pl",
                "asdfasdf77",
                "habemus papam",
                setGameEntitySet(),
                setPreviousGameEntityList(),
                setAvailabilityTimeEntityList()));
        assertEquals(exceptedList, userMapper.convertToDtoList(userEntityList));
    }

    @Test
    public void convertToEntityList() {
        List<UserEntity> exceptedList = new ArrayList<>();
        exceptedList.add(new UserEntity(0L,
                "Arkadiusz",
                "Blejwas",
                "gothicarka@wp.pl",
                "lolek55",
                "xD",
                setGameEntitySet(),
                setPreviousGameEntityList(),
                setAvailabilityTimeEntityList()));
        exceptedList.add(new UserEntity(1L,
                "Maciej",
                "Kowalski",
                "mackokowalski99@wp.pl",
                "mackomacko11",
                "carpe diem",
                setGameEntitySet(),
                setPreviousGameEntityList(),
                setAvailabilityTimeEntityList()));
        exceptedList.add(new UserEntity(2L,
                "Aleksandra",
                "Nowak",
                "olanowak99@wp.pl",
                "asdfasdf77",
                "habemus papam",
                setGameEntitySet(),
                setPreviousGameEntityList(),
                setAvailabilityTimeEntityList()));

        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(new UserDTO(0L,
                "Arkadiusz",
                "Blejwas",
                "gothicarka@wp.pl",
                "lolek55",
                "xD",
                setGameDTOSet(),
                setPreviousGameDTOList(),
                setAvailabilityTimeDTOList()));
        userDTOList.add(new UserDTO(1L,
                "Maciej",
                "Kowalski",
                "mackokowalski99@wp.pl",
                "mackomacko11",
                "carpe diem",
                setGameDTOSet(),
                setPreviousGameDTOList(),
                setAvailabilityTimeDTOList()));
        userDTOList.add(new UserDTO(2L,
                "Aleksandra",
                "Nowak",
                "olanowak99@wp.pl",
                "asdfasdf77",
                "habemus papam",
                setGameDTOSet(),
                setPreviousGameDTOList(),
                setAvailabilityTimeDTOList()));
        assertEquals(exceptedList, userMapper.convertToEntityList(userDTOList));
    }
}