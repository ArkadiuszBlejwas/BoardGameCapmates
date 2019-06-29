package com.capgemini.jstk.springAplication.PreviousGame;

import com.capgemini.jstk.springAplication.Aplication.SpringAplication;
import com.capgemini.jstk.springAplication.User.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAplication.class)
public class PreviousGameServiceIntegrationTest {

    @Autowired
    PreviousGameService previousGameService;

    @Autowired
    UserService userService;

    @Before
    public void callSetup(){
        userService.callSetup();
        previousGameService.callSetup();
    }

    @Test
    public void shouldGetPreviousGamesByIdUser(){
        List<PreviousGameDTO> exceptedList = new ArrayList<>();
        List<PreviousGameDTO> actualList = previousGameService.getPreviousGamesByIdUser(0L);
        assertEquals(exceptedList, actualList);
    }

    @Test
    public void shouldGetPreviousGameByIdPreviousGame(){
        List<Long> idPlayers = new ArrayList<>();
        idPlayers.add(0L);
        idPlayers.add(2L);
        PreviousGameDTO exceptedPrevGame = new PreviousGameDTO(0L, 0L, LocalDateTime.of(2019, Month.APRIL, 2, 21, 00), idPlayers, true);
        PreviousGameDTO prevGame = previousGameService.getPrevGameById(0L);
        assertEquals(exceptedPrevGame, prevGame);
    }

    @Test
    public void shouldAddPreviousGameToSystem(){
        List<Long> idPlayers = new ArrayList<>();
        idPlayers.add(0L);
        idPlayers.add(2L);
        PreviousGameDTO exceptedPrevGame = new PreviousGameDTO.PreviousGameDTOBuilder()
                .idPreviousGame(0L)
                .idPlayers(idPlayers)
                .idGame(0L)
                .isWinner(true)
                .timeGame(LocalDateTime.of(2019, Month.APRIL, 2, 21, 0))
                .build();
        previousGameService.addPreviousGameToSystem(exceptedPrevGame);
        PreviousGameDTO actualPrevGame = previousGameService.getPrevGameById(0L);
        assertEquals(exceptedPrevGame, actualPrevGame);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldRemovePreviousGameFromSystem(){
        previousGameService.removePreviousGameFromSystem(0L);
        previousGameService.getPrevGameById(0L);
    }

    @Test
    public void shouldUpdatePreviousGame(){
        PreviousGameDTO exceptedPrevGame = new PreviousGameDTO.PreviousGameDTOBuilder()
                .idPreviousGame(1L)
                .idGame(1L)
                .timeGame(LocalDateTime.of(2019, Month.MAY, 2, 21, 00))
                .idPlayers(new ArrayList<>())
                .isWinner(false)
                .build();
        previousGameService.updatePreviousGameInSystem(exceptedPrevGame);
        PreviousGameDTO actualPrevGame = previousGameService.getPrevGameById(1L);
        assertEquals(exceptedPrevGame, actualPrevGame);
    }
}