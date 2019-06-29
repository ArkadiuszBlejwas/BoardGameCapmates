package com.capgemini.jstk.springAplication.User;

import com.capgemini.jstk.springAplication.AvailabilityTime.AvailabilityTimeMapper;
import com.capgemini.jstk.springAplication.Game.GameMapper;
import com.capgemini.jstk.springAplication.Interfaces.Mapper;
import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements Mapper<UserEntity, UserDTO> {

    GameMapper gameMapper;
    PreviousGameMapper previousGameMapper;
    AvailabilityTimeMapper availabilityTimeMapper;

    public UserMapper(GameMapper gameMapper, PreviousGameMapper previousGameMapper, AvailabilityTimeMapper availabilityTimeMapper) {
        this.gameMapper = gameMapper;
        this.previousGameMapper = previousGameMapper;
        this.availabilityTimeMapper = availabilityTimeMapper;
    }


    public UserDTO convertToDto(UserEntity userEntity){
        if (userEntity == null){
            return null;
        }
        return new UserDTO(
                userEntity.getIdUser(),
                userEntity.getFirstName(),
                userEntity.getSurName(),
                userEntity.getEMail(),
                userEntity.getPassword(),
                userEntity.getLifeMotto(),
                gameMapper.convertToDtoSet(userEntity.getMyGames()),
                previousGameMapper.convertToDtoList(userEntity.getPreviousGames()),
                availabilityTimeMapper.convertToDtoList(userEntity.getAvailabilityTime()));
    }

    public UserEntity convertToEntity(UserDTO userDTO){
        if (userDTO == null){
            return null;
        }
        return new UserEntity(
                userDTO.getIdUser(),
                userDTO.getFirstName(),
                userDTO.getSurName(),
                userDTO.getEMail(),
                userDTO.getPassword(),
                userDTO.getLifeMotto(),
                gameMapper.convertToEntitySet(userDTO.getMyGames()),
                previousGameMapper.convertToEntityList(userDTO.getPreviousGames()),
                availabilityTimeMapper.convertToEntityList(userDTO.getAvailabilityTime()));
    }

    public List<UserDTO> convertToDtoList(List<UserEntity> userEntityList){
        return userEntityList.stream()
                .map(user -> new UserDTO(
                        user.getIdUser(),
                        user.getFirstName(),
                        user.getSurName(),
                        user.getEMail(),
                        user.getPassword(),
                        user.getLifeMotto(),
                        gameMapper.convertToDtoSet(user.getMyGames()),
                        previousGameMapper.convertToDtoList(user.getPreviousGames()),
                        availabilityTimeMapper.convertToDtoList(user.getAvailabilityTime())))
                .collect(Collectors.toList());
    }

    public List<UserEntity> convertToEntityList(List<UserDTO> userDTOList){
        return userDTOList.stream()
                .map(user -> new UserEntity(
                        user.getIdUser(),
                        user.getFirstName(),
                        user.getSurName(),
                        user.getEMail(),
                        user.getPassword(),
                        user.getLifeMotto(),
                        gameMapper.convertToEntitySet(user.getMyGames()),
                        previousGameMapper.convertToEntityList(user.getPreviousGames()),
                        availabilityTimeMapper.convertToEntityList(user.getAvailabilityTime())))
                .collect(Collectors.toList());
    }
}
