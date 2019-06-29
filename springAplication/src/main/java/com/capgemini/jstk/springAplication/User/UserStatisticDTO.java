package com.capgemini.jstk.springAplication.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserStatisticDTO {

    private Long positionInRanking;
    private UserLevel level;
    private Long numberOfGamesWon;
    private Long numberOfGamesLost;
}
