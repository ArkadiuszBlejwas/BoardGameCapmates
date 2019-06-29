package com.capgemini.jstk.springAplication.Game;

import com.capgemini.jstk.springAplication.Interfaces.GameRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
public class GameDAO implements GameRepository {

    private List<GameEntity> games;
    private Long idGame = 5L;

    public void setupGameDAO(){
        games = new ArrayList<>();
        games.add(new GameEntity( 0L, "Domino", 2, 2));
        games.add(new GameEntity( 1L, "Warcaby", 2, 2));
        games.add(new GameEntity( 2L, "Szachy", 2, 2));
        games.add(new GameEntity( 3L, "Chińczyk", 2, 4));
        games.add(new GameEntity( 4L, "Monopoly", 2, 8));
        games.add(new GameEntity( 5L, "Shogi", 2, 2));
    }

    public GameEntity get(Long idGame){
        checkArgumentIsNotNull(idGame);
        for (GameEntity game : getAll()){
            if (game.getIdGame() != null && game.getIdGame().equals(idGame)){
                return game;
            }
        }
        throw new IndexOutOfBoundsException("You given incorrect index");
    }

    public List<GameEntity> getAll(){
        return games;
    }

    public GameEntity add(GameEntity gameEntity){
        checkArgumentIsNotNull(gameEntity);
        gameEntity.setIdGame(++idGame);
        if (games.contains(gameEntity)){
            throw new IllegalArgumentException("Such game already exists");
        }
        games.add(gameEntity);
        return gameEntity;
    }

    public GameEntity remove(GameEntity gameEntity){
        checkArgumentIsNotNull(gameEntity);
        if (!getAll().contains(gameEntity)){
            throw new IllegalArgumentException("Database doesn't have such gameEntity");
        }
        games.remove(gameEntity);
        return gameEntity;
    }
}
