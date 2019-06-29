package com.capgemini.jstk.springAplication.PreviousGame;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PreviousGameDTO {

    @EqualsAndHashCode.Exclude
    private Long idPreviousGame;
    private Long idGame;
    private LocalDateTime timeGame;
    private List<Long> idPlayers;
    private boolean isWinner;
}
