package com.capgemini.jstk.springAplication.PreviousGame;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PreviousGameEntity {

    private Long idPreviousGame;
    private Long idGame;
    private LocalDateTime timeGame;
    private List<Long> idPlayers;
    private boolean isWinner;
}
