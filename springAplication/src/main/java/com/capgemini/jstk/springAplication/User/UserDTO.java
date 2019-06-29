package com.capgemini.jstk.springAplication.User;

import com.capgemini.jstk.springAplication.AvailabilityTime.AvailabilityTimeDTO;
import com.capgemini.jstk.springAplication.AvailabilityTime.AvailabilityTimeEntity;
import com.capgemini.jstk.springAplication.Game.GameDTO;
import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameDTO;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long idUser;
    private String firstName;
    private String surName;
    private String eMail;
    private String password;
    private String lifeMotto;

    private Set<GameDTO> myGames;
    private List<PreviousGameDTO> previousGames;

    private List<AvailabilityTimeDTO> availabilityTime;

}

