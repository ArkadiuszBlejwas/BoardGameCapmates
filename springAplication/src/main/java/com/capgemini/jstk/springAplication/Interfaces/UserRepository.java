package com.capgemini.jstk.springAplication.Interfaces;

import com.capgemini.jstk.springAplication.User.UserEntity;

public interface UserRepository extends DAO<UserEntity> {

    //List<UserEntity> findUserById(long id);

    //Long getId(UserEntity entity);

    //metody które wyszukują użytkownika po konkretnych polach
    void setupUserDAO();

}
