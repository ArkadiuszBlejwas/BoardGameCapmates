package com.capgemini.jstk.springAplication.PreviousGame;

import com.capgemini.jstk.springAplication.Interfaces.PreviousGameRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PreviousGameDAO implements PreviousGameRepository {

    private List<PreviousGameEntity> playedGames;
    private Long idPrev = 1L;

    public void setupPreviousGameDAO(){
        playedGames = new ArrayList<>();
        List<Long> idPlayers = new ArrayList<>();
        idPlayers.add(0L);
        idPlayers.add(2L);
        playedGames.add(new PreviousGameEntity(0L, 0L, LocalDateTime.of(2019, Month.APRIL, 2, 21, 00), idPlayers, true));
        playedGames.add(new PreviousGameEntity(1L, 0L, LocalDateTime.of(2018, Month.APRIL, 2, 21, 00), idPlayers, true));
    }

    public PreviousGameDAO(){
        playedGames = new ArrayList<>();
    }

    public PreviousGameEntity get(Long idPrev){
        checkArgumentIsNotNull(idPrev);
        for (PreviousGameEntity prev : getAll()){
            if (prev.getIdPreviousGame() != null && prev.getIdPreviousGame().equals(idPrev)){
                return prev;
            }
        }
        throw new IndexOutOfBoundsException("You given incorrect index");
    }

    public List<PreviousGameEntity> getAll(){
        return playedGames;
    }

    public PreviousGameEntity add(PreviousGameEntity previousGameEntity){
        checkArgumentIsNotNull(previousGameEntity);
        previousGameEntity.setIdPreviousGame(++idPrev);
        playedGames.add(previousGameEntity);
        return previousGameEntity;
    }

    public PreviousGameEntity remove(PreviousGameEntity previousGameEntity){
        checkArgumentIsNotNull(previousGameEntity);
        if (!getAll().contains(previousGameEntity)){
            throw new IllegalArgumentException("Database doesn't have such previousGameEntity");
        }
        playedGames.remove(previousGameEntity);
        return previousGameEntity;
    }

    public PreviousGameEntity update(PreviousGameEntity previousGameEntity){
        checkArgumentIsNotNull(previousGameEntity);
        for (PreviousGameEntity prev : getAll()) {
            if (prev.getIdPreviousGame() != null && prev.getIdPreviousGame().equals(previousGameEntity.getIdPreviousGame())) {
                prev.setIdPlayers(previousGameEntity.getIdPlayers());
                prev.setIdGame(previousGameEntity.getIdGame());
                prev.setTimeGame(previousGameEntity.getTimeGame());
                prev.setWinner(previousGameEntity.isWinner());
                return prev;
            }
        }
        throw new IllegalArgumentException("There is no previous game with such id");
    }
}
