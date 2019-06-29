package com.capgemini.jstk.springAplication.Interfaces;

import com.capgemini.jstk.springAplication.User.UserEntity;

import java.util.List;

public interface DAO<E> {
    default E get(Long id){
        return null;
    }

    default List<E> getAll(){
        return null;
    }

    default E add(E object){
        return null;
    }

    default E remove(E object){
        return null;
    }

    default E update(E object){
        return null;
    }

    default void checkArgumentIsNotNull(Object object){
        if (object == null){
            throw new IllegalArgumentException("You given null in argument");
        }
    }
}
