package com.capgemini.jstk.springAplication.User;

import com.capgemini.jstk.springAplication.AvailabilityTime.AvailabilityTimeEntity;
import com.capgemini.jstk.springAplication.Game.GameEntity;
import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor

public class UserEntity {

    private Long idUser;
    private String firstName;
    private String surName;
    private String eMail;
    private String password;
    private String lifeMotto;

    private Set<GameEntity> myGames;
    private List<PreviousGameEntity> previousGames;

    private List<AvailabilityTimeEntity> availabilityTime;

}
