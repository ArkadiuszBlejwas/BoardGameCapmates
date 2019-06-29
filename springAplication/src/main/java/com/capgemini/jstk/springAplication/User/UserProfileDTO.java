package com.capgemini.jstk.springAplication.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class UserProfileDTO {

    private Long idUser;
    private String firstName;
    private String surName;
    private String eMail;
    private String password;
    private String lifeMotto;
}
