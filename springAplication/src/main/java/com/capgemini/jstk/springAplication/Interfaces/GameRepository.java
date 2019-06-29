package com.capgemini.jstk.springAplication.Interfaces;

import com.capgemini.jstk.springAplication.Game.GameEntity;

public interface GameRepository extends DAO<GameEntity> {

    void setupGameDAO();
}
