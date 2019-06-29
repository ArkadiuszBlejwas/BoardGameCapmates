package com.capgemini.jstk.springAplication.User;

import com.capgemini.jstk.springAplication.Interfaces.UserRepository;
import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameDTO;
import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameEntity;
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
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Before
    public void setup(){
        List<UserEntity> usersEntity = new ArrayList<>();
        usersEntity.add(new UserEntity(0L,
                "Arkadiusz",
                "Blejwas",
                "gothicarka@wp.pl",
                "lolek55",
                "xD",
                new HashSet<>(),
                new ArrayList<>(),
                new ArrayList<>()));
        usersEntity.add(new UserEntity(1L,
                "Maciej",
                "Kowalski",
                "mackokowalski99@wp.pl",
                "mackomacko11",
                "carpe diem",
                new HashSet<>(),
                Arrays.asList(
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2019, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2018, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2017, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2016, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2015, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true)),
                new ArrayList<>()));
        usersEntity.add(new UserEntity(2L,
                "Aleksandra",
                "Nowak",
                "olanowak99@wp.pl",
                "asdfasdf77",
                "habemus papam",
                new HashSet<>(),
                Arrays.asList(
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2009, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2008, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2007, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2006, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2005, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2004, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2003, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2002, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2001, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameEntity(0L,0L, LocalDateTime.of(2000, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true)),
                new ArrayList<>()));
        userService = new UserService(userRepository, userMapper);
        when(userRepository.getAll()).thenReturn(usersEntity);
        when(userRepository.get(0L)).thenReturn(usersEntity.get(0));
        when(userRepository.get(1L)).thenReturn(usersEntity.get(1));
        when(userRepository.get(2L)).thenReturn(usersEntity.get(2));

        List<UserDTO> usersDTO = new ArrayList<>();
        usersDTO.add(new UserDTO(0L,
                "Arkadiusz",
                "Blejwas",
                "gothicarka@wp.pl",
                "lolek55",
                "xD",
                new HashSet<>(),
                new ArrayList<>(),
                new ArrayList<>()));
        usersDTO.add(new UserDTO(1L,
                "Maciej",
                "Kowalski",
                "mackokowalski99@wp.pl",
                "mackomacko11",
                "carpe diem",
                new HashSet<>(),
                Arrays.asList(
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2019, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2018, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2017, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2016, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2015, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true)),
                new ArrayList<>()));
        usersDTO.add(new UserDTO(2L,
                "Aleksandra",
                "Nowak",
                "olanowak99@wp.pl",
                "asdfasdf77",
                "habemus papam",
                new HashSet<>(),
                Arrays.asList(
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2009, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2008, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2007, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2006, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2005, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2004, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2003, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2002, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2001, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2000, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true)),
                new ArrayList<>()));
        when(userMapper.convertToDtoList(usersEntity)).thenReturn(usersDTO);
        when(userMapper.convertToDto(usersEntity.get(0))).thenReturn(usersDTO.get(0));
        when(userMapper.convertToDto(usersEntity.get(1))).thenReturn(usersDTO.get(1));
        when(userMapper.convertToDto(usersEntity.get(2))).thenReturn(usersDTO.get(2));
//        when(userMapper.convertToEntityList(usersDTO)).thenReturn(usersEntity);
//        when(userMapper.convertToEntity(usersDTO.get(0))).thenReturn(usersEntity.get(0));
//        when(userMapper.convertToEntity(usersDTO.get(1))).thenReturn(usersEntity.get(1));
//        when(userMapper.convertToEntity(usersDTO.get(2))).thenReturn(usersEntity.get(2));
    }

    @Test
    public void shouldGetUserById() {
        final Long idUser = 0L;
        final UserDTO exceptedUser = new UserDTO(0L,
                "Arkadiusz",
                "Blejwas",
                "gothicarka@wp.pl",
                "lolek55",
                "xD",
                new HashSet<>(),
                new ArrayList<>(),
                new ArrayList<>());
        UserDTO result = userService.getUserById(idUser);
        assertEquals(exceptedUser, result);
    }

    @Test
    public void shouldGetAllUsers() {
        List<UserDTO> exceptedUsers = new ArrayList<>();
        exceptedUsers.add(new UserDTO(0L,
                "Arkadiusz",
                "Blejwas",
                "gothicarka@wp.pl",
                "lolek55",
                "xD",
                new HashSet<>(),
                new ArrayList<>(),
                new ArrayList<>()));
        exceptedUsers.add(new UserDTO(1L,
                "Maciej",
                "Kowalski",
                "mackokowalski99@wp.pl",
                "mackomacko11",
                "carpe diem",
                new HashSet<>(),
                Arrays.asList(
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2019, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2018, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2017, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2016, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2015, Month.FEBRUARY, 20, 16, 30), Arrays.asList(0L, 1L), true)),
                new ArrayList<>()));
        exceptedUsers.add(new UserDTO(2L,
                "Aleksandra",
                "Nowak",
                "olanowak99@wp.pl",
                "asdfasdf77",
                "habemus papam",
                new HashSet<>(),
                Arrays.asList(
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2009, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2008, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2007, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2006, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2005, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2004, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2003, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2002, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2001, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true),
                        new PreviousGameDTO(0L,0L, LocalDateTime.of(2000, Month.FEBRUARY, 20, 16, 30), Arrays.asList(2L, 1L), true)),
                new ArrayList<>()));
        List<UserDTO> result = userService.getAllUsers();
        assertEquals(exceptedUsers, result);
    }

    @Test
    public void shouldGetUserProfile() {
        final UserProfileDTO exceptedUser = new UserProfileDTO.UserProfileDTOBuilder()
                .idUser(0L)
                .firstName("Arkadiusz")
                .surName("Blejwas")
                .eMail("gothicarka@wp.pl")
                .password("lolek55")
                .lifeMotto("xD")
                .build();
        UserProfileDTO result = userService.getUserProfile(0L);
        assertEquals(exceptedUser, result);
    }

    @Test
    public void shouldGetUserStatistic() {
        final UserStatisticDTO exceptedStatistic = new UserStatisticDTO.UserStatisticDTOBuilder()
                .level(UserLevel.Beginner)
                .numberOfGamesWon(0L)
                .numberOfGamesLost(0L)
                .positionInRanking(3L)
                .build();
        UserStatisticDTO result = userService.getUserStatistic(0L);
        assertEquals(exceptedStatistic, result);
    }

//    @Test
//    public void shouldUpdateUserProfile() {
//        final UserDTO exceptedUser = new UserDTO.UserDTOBuilder()
//                .idUser(0L)
//                .firstName("Janusz")
//                .surName("Tracz")
//                .eMail("janusz.tracz@gmail.com")
//                .password("asdf1234")
//                .lifeMotto("lalala")
//                .myGames(new HashSet<GameDTO>())
//                .previousGames(new ArrayList<PreviousGameDTO>())
//                .availabilityTime(new ArrayList<AvailabilityTimeDTO>())
//                .build();
//        userService.updateUserProfile(exceptedUser);
//        UserDTO result = userService.getUserById(0L);
//        assertEquals(exceptedUser, result);
//    }

    @Test
    public void getGeneralStatistic() {
        Map<Long, Long> exceptedStatistic = new HashMap<>();
        exceptedStatistic.put(2L, 10L);
        exceptedStatistic.put(1L, 5L);
        exceptedStatistic.put(0L, 0L);
        Map<Long, Long> resultStatistic = userService.getGeneralStatistic(userService.calculateGeneralStatistic());
        assertEquals(exceptedStatistic, resultStatistic);
    }
}