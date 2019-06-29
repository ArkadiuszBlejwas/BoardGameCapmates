package com.capgemini.jstk.springAplication.Interfaces;

import java.util.List;

public interface Mapper<Entity, Dto> {

    Dto convertToDto(Entity entity);

    Entity convertToEntity(Dto dto);

    List<Dto> convertToDtoList (List<Entity> entityList);

    List<Entity> convertToEntityList (List<Dto> dtoList);
}
