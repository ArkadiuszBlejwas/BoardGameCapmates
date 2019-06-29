package com.capgemini.jstk.springAplication.User;

import com.capgemini.jstk.springAplication.Aplication.SpringAplication;
import com.capgemini.jstk.springAplication.Game.GameDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAplication.class)
public class UserServiceIntegrationTest {

    @Autowired
    UserService userService;

    @Before
    public void callSetup(){
        userService.callSetup();
    }

    @Test
    public void shouldGetUserById0L() {
        UserDTO userDTO = userService.getUserById(0L);
        List<GameDTO> emptyList = new ArrayList<>();
        assertEquals("Arkadiusz", userDTO.getFirstName());
        assertEquals(emptyList, userDTO.getPreviousGames());
    }

    @Test
    public void shouldGetAllUsers() {
        List<UserDTO> list = userService.getAllUsers();
        assertEquals("Blejwas", list.get(0).getSurName());
        assertEquals("Kowalski", list.get(1).getSurName());
        assertEquals("Nowak", list.get(2).getSurName());
    }

    @Test
    public void shouldGetProfileFirstUserInDataBase() {
        UserProfileDTO profile = userService.getUserProfile(0L);
        assertEquals("Arkadiusz", profile.getFirstName());
        assertEquals("xD", profile.getLifeMotto());
    }

    @Test
    public void shouldGetStatisticFirstUserInDataBase() {
        UserStatisticDTO statistic = userService.getUserStatistic(0L);
        assertEquals(UserLevel.Beginner, statistic.getLevel());
    }

    @Test
    public void shouldUpdateUserProfile(){
        UserDTO userDTO = new UserDTO(
                2L,
                "Natalia",
                "Nowak",
                "lalala111@wp.pl",
                "1234!!!!",
                "kocham mame",
                new HashSet<>(),
                new ArrayList<>(),
                new ArrayList<>());
        userService.updateUserProfile(userDTO);
        assertEquals("Natalia", userService.getUserById(2L).getFirstName());
        assertEquals("lalala111@wp.pl", userService.getUserById(2L).getEMail());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldRemoveUserProfile(){
        userService.removeUserProfile(2L);
        userService.getUserById(2L);
    }

    @Test
    public void shouldAddNewUser(){
        UserDTO exceptedUser = new UserDTO.UserDTOBuilder()
                .idUser(3L)
                .firstName("Karol")
                .surName("Wojtyła")
                .eMail("JPII@onet.pl")
                .password("watykan777")
                .lifeMotto("totus tuus")
                .myGames(new HashSet<>())
                .previousGames(new ArrayList<>())
                .availabilityTime(new ArrayList<>())
                .build();
        userService.addUserProfile(exceptedUser);
        UserDTO user = userService.getUserById(3L);
        assertEquals(exceptedUser, user);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowsExceptionWhenTryRemoveUserWhoDoesntExist(){
        userService.removeUserProfile(10L);
    }

    @Test
    public void shouldEnterCorrectIdUserWhenTryAddUserWithAlreadyExistitingId(){
        UserDTO userDTO = new UserDTO.UserDTOBuilder()
                .idUser(0L)
                .firstName("Mariusz")
                .surName("Bob")
                .eMail("mariusz.bob@wp.pl")
                .password("BobBudowniczy11")
                .lifeMotto("róbta co chceta")
                .myGames(new HashSet<>())
                .previousGames(new ArrayList<>())
                .availabilityTime(new ArrayList<>())
                .build();
        userService.addUserProfile(userDTO);
        userDTO.setIdUser(3L);
        assertEquals(userDTO, userService.getUserById(3L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsExceptionWhenTryTypeIncorrectEmail(){
        String incorrectEmail = "@mail@wp.pl";
        UserDTO userDTO = new UserDTO.UserDTOBuilder()
                .idUser(6L)
                .firstName("Arek")
                .surName("Konrad")
                .eMail(incorrectEmail)
                .password("ccc")
                .lifeMotto("nie wiem")
                .myGames(new HashSet<>())
                .availabilityTime(new ArrayList<>())
                .previousGames(new ArrayList<>())
                .build();
        userService.addUserProfile(userDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsExceptionWhenTryGetPositionRankingGivingIncorrectIdUser(){
        userService.getPositionRanking(15L);
    }
}