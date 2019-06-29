package com.capgemini.jstk.springAplication.User;

import com.capgemini.jstk.springAplication.Game.GameDTO;
import com.capgemini.jstk.springAplication.Interfaces.UserRepository;
import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMap){
        this.userRepository = userRepository;
        this.userMapper = userMap;
    }

    /**
     * This method calls userMapper and userRepository in order to acquire user from database
     * @param idUser identity number of user in database
     * @return userDTO mapped from userEntity acquired from database
     */
    public UserDTO getUserById(Long idUser){
        return userMapper.convertToDto(userRepository.get(idUser));
    }

    /**
     * This method calls userRepository in order to acquire all users from database
     * @return list of userDTO mapped from list of userEntity acquired from database
     */
    public List<UserDTO> getAllUsers(){
        return userMapper.convertToDtoList(userRepository.getAll());
    }

    /**
     * This method returns basic user data. Calls getUserById method in order to give user profile
     * @param idUser identity number of user in database
     * @return userProfileDTO acquired from userDTO
     */
    public UserProfileDTO getUserProfile(Long idUser){
        UserDTO user = getUserById(idUser);
        return new UserProfileDTO(
                user.getIdUser(),
                user.getFirstName(),
                user.getSurName(),
                user.getEMail(),
                user.getPassword(),
                user.getLifeMotto());
    }

    /**
     * This method returns statistics of user
     * @param idUser identity number of user in database
     * @return object of type UserStatisticDTO. This object contains fields such as:
     * position of user in ranking, level, number of games won and number of games lost.
     */
    public UserStatisticDTO getUserStatistic(Long idUser){
        return new UserStatisticDTO(
                getPositionRanking(idUser),
                calculateUserLevel(idUser),
                countNumberOfGamesWon(idUser),
                countNumberOfGamesLost(idUser));
    }

    /**
     * This method updates user profile(all fields in userEntity in database)
     * @param userDTO object contains new user data except new idUser.
     * Contains already existing idUser in database in order to identify userEntity which has to be updated
     */
    public void updateUserProfile(UserDTO userDTO){
        validateEmail(userDTO.getEMail());
        UserEntity user = userMapper.convertToEntity(userDTO);
        userRepository.update(user);
    }

    /**
     * This method remove user profile from database
     * @param idUser identity number of user in database
     */
    public void removeUserProfile(Long idUser){
        UserEntity user = userMapper.convertToEntity(getUserById(idUser));
        userRepository.remove(user);
    }

    /**
     * This method adds new user profile to database
     * @param userDTO object contains new user data
     * @return userDTO object contains new user data
     */
    public UserDTO addUserProfile(UserDTO userDTO){
        validateEmail(userDTO.getEMail());
        UserEntity user = userMapper.convertToEntity(userDTO);
        userRepository.add(user);
        return userDTO;
    }

    private Long countNumberOfGamesWon(Long idUser){
        return getUserById(idUser).getPreviousGames().stream()
                .filter(winner -> winner.isWinner() == true).count();
    }

    private Long countNumberOfGamesLost(Long idUser){
        return getUserById(idUser).getPreviousGames().stream()
                .filter(winner -> winner.isWinner() == false).count();
    }

    private UserLevel calculateUserLevel(Long idUser){
        Long counter = countNumberOfGamesWon(idUser);
        if (counter >= 10 && counter < 20){
            return UserLevel.Intermidiate;
        }
        else if (counter >= 20 && counter < 30){
            return UserLevel.Advanced;
        }
        else if (counter >= 30 && counter < 40){
            return UserLevel.Master;
        }
        else if (counter >= 40){
            return UserLevel.PastMaster;
        }
        return UserLevel.Beginner;
    }

    /**
     * This method calculate general statistic of all users in database
     * @return unsorted map which key is idUser and value is number of games won
     */
    public Map<Long, Long> calculateGeneralStatistic() {
        List<UserDTO> userDTOList = getAllUsers();
        Map<Long, Long> unsortedMap = new HashMap<>();
        for (UserDTO user : userDTOList) {
            Long idUser = user.getIdUser();
            Long counter = countNumberOfGamesWon(idUser);
            unsortedMap.put(idUser, counter);
        }
        return unsortedMap;
    }

    /**
     * This method sorts general statistics od users in order create winner ranking
     * @param unsortedMap map which key is idUser and value is number of games won
     * @return sorted map which key is idUser and value is number of games won.
     * This map constitutes winner ranking
     */
    public Map<Long, Long> getGeneralStatistic(Map<Long, Long> unsortedMap){
        List<Map.Entry<Long, Long>> list = new LinkedList<>(unsortedMap.entrySet());
        Collections.sort(list, (value1, value2) -> (value2.getValue()).compareTo(value1.getValue()));
        Map<Long, Long> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Long, Long> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    /**
     * This method calculate position specified user in ranking
     * @param idUser identity number of user in database
     * @return position specified user in general ranking of all users
     */
    public Long getPositionRanking(Long idUser){
        Map<Long, Long> map = getGeneralStatistic(calculateGeneralStatistic());
        Set<Map.Entry<Long, Long>> entrySet = map.entrySet();
        Long position = 1L;
        for (Map.Entry<Long, Long> entry : entrySet){
            if(entry.getKey().equals(idUser)){
                return position;
            }
            ++position;
        }
        throw new IllegalArgumentException("There is no such user in database");
    }
    
    private static void validateEmail(String email) {
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            if (!email.matches(regex)){
                throw new IllegalArgumentException("Format of email is not correct");
            }
        }

    /**
     * This method loads alreadymade database
      */
    public void callSetup(){
        userRepository.setupUserDAO();
    }

    public List<UserDTO> findUserByParameters(UserDTO user) {
        List<UserDTO> allUsers = getAllUsers();
        if(user.getFirstName() != null && user.getFirstName() != "") {
            allUsers = findUsersByFirstName(allUsers, user.getFirstName());
        }
        if (user.getSurName() != null && user.getSurName() != "") {
            allUsers = findUsersBySurName(allUsers, user.getSurName());
        }
        if (user.getMyGames() != null && !user.getMyGames().isEmpty()){
            allUsers = findUsersByGames(allUsers, user.getMyGames());
        }
        return allUsers;
    }

    private List<UserDTO> findUsersByFirstName(List<UserDTO> allUsers, String firstName){
        return allUsers.stream()
                .filter(user -> user.getFirstName().toUpperCase().contains(firstName.toUpperCase()))
                .collect(Collectors.toList());
    }

    private List<UserDTO> findUsersBySurName(List<UserDTO> allUsers, String surName){
        return allUsers.stream()
                .filter(user -> user.getSurName().toUpperCase().contains(surName.toUpperCase()))
                .collect(Collectors.toList());
    }

    private List<UserDTO> findUsersByGames(List<UserDTO> allUsers, Set<GameDTO> gameDTOSet){
        List<UserDTO> newList = new ArrayList<>();
        for (UserDTO user : allUsers){
            if (user.getMyGames() != null){
                for (GameDTO userGame : user.getMyGames()){
                    if (userGame != null){
                        for (GameDTO game : gameDTOSet){
                            if (userGame.getNameGame().toUpperCase().contains(game.getNameGame().toUpperCase())){
                                newList.add(user);
                            }
                        }
                    }
                }
            }
        }
        return newList;
    }
}
