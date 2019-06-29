package com.capgemini.jstk.springAplication.AvailabilityTime;

import com.capgemini.jstk.springAplication.Interfaces.AvailabilityTimeRepository;
import com.capgemini.jstk.springAplication.User.UserDTO;
import com.capgemini.jstk.springAplication.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityTimeService {

    private AvailabilityTimeRepository availabilityTimeRepository;
    private AvailabilityTimeMapper availabilityTimeMapper;
    private UserService userService;

    @Autowired
    public AvailabilityTimeService(
            AvailabilityTimeRepository availabilityTimeRepository,
            UserService userService,
            AvailabilityTimeMapper availabilityTimeMapper){
    this.availabilityTimeRepository = availabilityTimeRepository;
    this.userService = userService;
    this.availabilityTimeMapper = availabilityTimeMapper;
    }

    public List<AvailabilityTimeDTO> getAllAvailabilityTime(Long idUser){
        UserDTO user = userService.getUserById(idUser);
        return user.getAvailabilityTime();
    }

    public void addAvailabilityTime(Long idUser, AvailabilityTimeDTO time){
        List<AvailabilityTimeDTO> timeDtoList = getAllAvailabilityTime(idUser);
        for (AvailabilityTimeDTO userTime : timeDtoList){
            if (userTime.getAvailabilityTimeFrom() != null && userTime.getAvailabilityTimeTo() != null){
                isThisTimeCoverageWithExistingTime(time, userTime);
            }
        }
        timeDtoList.add(time);
        setTime(idUser, timeDtoList);
    }

    private void isThisTimeCoverageWithExistingTime(AvailabilityTimeDTO time, AvailabilityTimeDTO userTime) {
        if ((time.getAvailabilityTimeFrom().isAfter(userTime.getAvailabilityTimeFrom())
                && time.getAvailabilityTimeFrom().isBefore(userTime.getAvailabilityTimeTo()))
                || (time.getAvailabilityTimeTo().isAfter(userTime.getAvailabilityTimeFrom())
                && time.getAvailabilityTimeTo().isBefore(userTime.getAvailabilityTimeTo()))
                || (time.getAvailabilityTimeFrom().isBefore(userTime.getAvailabilityTimeFrom())
                && time.getAvailabilityTimeTo().isAfter(userTime.getAvailabilityTimeTo()))){
            throw new IllegalArgumentException("This availability time coverages with the existing availability time");
        }
    }

    public void updateAvailabilityTime(Long idUser, AvailabilityTimeDTO time){
        List<AvailabilityTimeDTO> timeDtoList = getAllAvailabilityTime(idUser);
        for (AvailabilityTimeDTO userTime : timeDtoList) {
            if (userTime.getIdTime() != null && userTime.getIdTime().equals(time.getIdTime())) {
                userTime.setAvaible(time.isAvaible());
                userTime.setAvailabilityTimeFrom(time.getAvailabilityTimeFrom());
                userTime.setAvailabilityTimeTo(time.getAvailabilityTimeTo());
                userTime.setComment(time.getComment());
            }
        }
        setTime(idUser, timeDtoList);
    }

    public void removeAvailabilityTime(Long idUser, Long idTime, String comment){
        List<AvailabilityTimeDTO> timeDtoList = getAllAvailabilityTime(idUser);
        for (AvailabilityTimeDTO userTime : timeDtoList){
            if (userTime.getIdTime() != null && userTime.getIdTime().equals(idTime)){
                userTime.setAvaible(false);
                userTime.setComment(comment);
            }
        }
        setTime(idUser, timeDtoList);
    }

    private void setTime(Long idUser, List<AvailabilityTimeDTO> timeDtoList){
        UserDTO user = userService.getUserById(idUser);
        user.setAvailabilityTime(timeDtoList);
        userService.updateUserProfile(user);
    }

    public void addAvailabilityTimeToSystem(AvailabilityTimeDTO availabilityTimeDTO){
        AvailabilityTimeEntity time = availabilityTimeMapper.convertToEntity(availabilityTimeDTO);
        availabilityTimeRepository.add(time);
    }

    public void updateAvailabilityTimeInSystem(AvailabilityTimeDTO availabilityTimeDTO){
        AvailabilityTimeEntity time = availabilityTimeMapper.convertToEntity(availabilityTimeDTO);
        availabilityTimeRepository.update(time);
    }

    public void removeAvailabilityTimeFromSystem(AvailabilityTimeDTO availabilityTimeDTO){
        AvailabilityTimeEntity time = availabilityTimeMapper.convertToEntity(availabilityTimeDTO);
        availabilityTimeRepository.remove(time);
    }

    public AvailabilityTimeDTO getAvailabilityTimeById(Long id){
        return availabilityTimeMapper.convertToDto(availabilityTimeRepository.get(id));
    }

    public List<AvailabilityTimeDTO> getAllAvailabilityTime(){
        return availabilityTimeMapper.convertToDtoList(availabilityTimeRepository.getAll());
    }

    public void callSetup(){
        availabilityTimeRepository.setupAvailabilityTimeDAO();
    }

}
