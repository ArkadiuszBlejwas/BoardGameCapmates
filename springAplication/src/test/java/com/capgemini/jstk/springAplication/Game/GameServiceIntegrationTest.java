package com.capgemini.jstk.springAplication.Game;

import com.capgemini.jstk.springAplication.Aplication.SpringAplication;
import com.capgemini.jstk.springAplication.User.UserDTO;
import com.capgemini.jstk.springAplication.User.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAplication.class)
public class GameServiceIntegrationTest {

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @Before
    public void callSetup(){
        gameService.callSetup();
        userService.callSetup();
    }

    @Test
    public void shouldGetGameById0L() {
        GameDTO gameDTO = gameService.getGameById(0L);
        assertEquals("Domino", gameDTO.getNameGame());
    }

    @Test
    public void shouldGetGamesFromSystem() {
        List<GameDTO> games = gameService.getGamesFromSystem();
        assertEquals("Domino", games.get(0).getNameGame());
        assertEquals("Warcaby", games.get(1).getNameGame());
        assertEquals("Shogi", games.get(5).getNameGame());
    }

    @Test
    public void shouldAddGameToSystem() {
        GameDTO gameDTO = new GameDTO(6L, "Kalambury", 2, 10);
        gameService.addGameToSystem(1L, gameDTO);
        UserDTO userDTO = userService.getUserById(1L);
        assertEquals("Kalambury", gameService.getGamesFromSystem().get(6).getNameGame());
        assertEquals("Kalambury", userDTO.getMyGames().iterator().next().getNameGame());
    }

    @Test
    public void shouldAddGameToMyCollectionFromSystem() {
        gameService.addGameToMyCollectionFromSystem(1L, 2L);
        assertEquals("Szachy", userService.getUserById(1L).getMyGames().iterator().next().getNameGame());
    }

    @Test
    public void shouldRemoveGameFromMyCollection() {
        gameService.addGameToMyCollectionFromSystem(1L, 2L);
        gameService.removeGameFromMyCollection(1L, 2L);
        Set<GameEntity> emptySet = new HashSet<>();
        assertEquals(emptySet, userService.getUserById(2L).getMyGames());
    }

    @Test
    public void getUserGames() {
        gameService.addGameToMyCollectionFromSystem(1L, 2L);
        gameService.addGameToMyCollectionFromSystem(1L, 3L);
        gameService.addGameToMyCollectionFromSystem(1L, 4L);
        Set<GameDTO> set = new HashSet<>();
        set.add(new GameDTO( 2L, "Szachy", 2, 2));
        set.add(new GameDTO( 3L, "Chińczyk", 2, 4));
        set.add(new GameDTO( 4L, "Monopoly", 2, 8));
        assertEquals(set, gameService.getUserGames(1L));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowsExceptionWhenTryGetGameWithIncorrectId(){
        gameService.getGameById(22L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsExceptionWhenTryRemoveNotExistingGame(){
        gameService.removeGameFromMyCollection(1L, 0L);
    }
}