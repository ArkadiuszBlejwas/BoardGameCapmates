package com.capgemini.jstk.springAplication.Game;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class GameDTO {

    @EqualsAndHashCode.Exclude
    private Long idGame;
    private String nameGame;
    private int minNumberOfPlayers;
    private int maxNumberOfPlayers;

}
