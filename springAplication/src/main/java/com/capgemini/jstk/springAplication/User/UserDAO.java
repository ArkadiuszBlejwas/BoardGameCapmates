package com.capgemini.jstk.springAplication.User;

import com.capgemini.jstk.springAplication.Aplication.SuchUserAlreadyExistException;
import com.capgemini.jstk.springAplication.Aplication.UserNotFoundException;
import com.capgemini.jstk.springAplication.Interfaces.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDAO implements UserRepository {

    private List<UserEntity> users = new ArrayList<UserEntity>();
    private Long idUser = 2L;

    public UserDAO(){
        setupUserDAO();
    }

    public void setupUserDAO(){
        users.add(new UserEntity(0L,
                "Arkadiusz",
                "Blejwas",
                "gothicarka@wp.pl",
                "lolek55",
                "xD",
                new HashSet<>(),
                new ArrayList<>(),
                new ArrayList<>()));
        users.add(new UserEntity(1L,
                "Maciej",
                "Kowalski",
                "mackokowalski99@wp.pl",
                "mackomacko11",
                "carpe diem",
                new HashSet<>(),
                new ArrayList<>(),
                new ArrayList<>()));
        users.add(new UserEntity(2L,
                "Aleksandra",
                "Nowak",
                "olanowak99@wp.pl",
                "asdfasdf77",
                "habemus papam",
                new HashSet<>(),
                new ArrayList<>(),
                new ArrayList<>()));
    }

    public UserEntity get(Long idUser){
        checkArgumentIsNotNull(idUser);
        for (UserEntity user : getAll()){
            if (user.getIdUser() != null && user.getIdUser().equals(idUser)){
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public List<UserEntity> getAll(){
        return users;
    }

    public UserEntity add(UserEntity userEntity){
        checkArgumentIsNotNull(userEntity);
        userEntity.setIdUser(++idUser);
        if (users.contains(userEntity)){
            throw new SuchUserAlreadyExistException();
        }
        users.add(userEntity);
        return userEntity;
    }

    public UserEntity remove(UserEntity userEntity){
        checkArgumentIsNotNull(userEntity);
        if (!getAll().contains(userEntity)){
            throw new UserNotFoundException();
        }
        users.remove(userEntity);
        return userEntity;
    }

    public UserEntity update(UserEntity userEntity){
        checkArgumentIsNotNull(userEntity);
        for (UserEntity user : getAll()){
            if (user.getIdUser() != null && user.getIdUser().equals(userEntity.getIdUser())){
                user.setFirstName(userEntity.getFirstName());
                user.setSurName(userEntity.getSurName());
                user.setEMail(userEntity.getEMail());
                user.setPassword(userEntity.getPassword());
                user.setLifeMotto(userEntity.getLifeMotto());
                user.setMyGames(userEntity.getMyGames());
                user.setPreviousGames(userEntity.getPreviousGames());
                user.setAvailabilityTime(userEntity.getAvailabilityTime());
                return user;
            }
        }
        throw new UserNotFoundException();
    }
}
