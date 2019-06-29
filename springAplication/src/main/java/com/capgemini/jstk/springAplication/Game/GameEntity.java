package com.capgemini.jstk.springAplication.Game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class GameEntity {

    private Long idGame;
    private String nameGame;
    private int minNumberOfPlayers;
    private int maxNumberOfPlayers;
}
