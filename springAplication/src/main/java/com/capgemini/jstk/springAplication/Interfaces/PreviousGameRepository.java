package com.capgemini.jstk.springAplication.Interfaces;

import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameEntity;

public interface PreviousGameRepository extends DAO<PreviousGameEntity> {

    void setupPreviousGameDAO();
}
