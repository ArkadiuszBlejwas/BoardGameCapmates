package com.capgemini.jstk.springAplication.AvailabilityTime;

import com.capgemini.jstk.springAplication.Interfaces.AvailabilityTimeRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
public class AvailabilityTimeDAO implements AvailabilityTimeRepository {

    private List<AvailabilityTimeEntity> calender;
    private Long idTime = 0L;

    public void setupAvailabilityTimeDAO(){
        calender = new ArrayList<>();
    }

    public AvailabilityTimeEntity get(Long idTime){
        checkArgumentIsNotNull(idTime);
        for (AvailabilityTimeEntity time : getAll()){
            if (time.getIdTime() != null && time.getIdTime().equals(idTime)){
                return time;
            }
        }
        throw new IndexOutOfBoundsException("You given incorrect index");
    }

    public List<AvailabilityTimeEntity> getAll(){
        return calender;
    }

    public AvailabilityTimeEntity add(AvailabilityTimeEntity availabilityTimeEntity){
        checkArgumentIsNotNull(availabilityTimeEntity);
        availabilityTimeEntity.setIdTime(idTime++);
        calender.add(availabilityTimeEntity);
        return availabilityTimeEntity;
    }

    public AvailabilityTimeEntity remove(AvailabilityTimeEntity availabilityTimeEntity){
        checkArgumentIsNotNull(availabilityTimeEntity);
        if (!getAll().contains(availabilityTimeEntity)){
            throw new IllegalArgumentException("Database doesn't have such availability time");
        }
        calender.remove(availabilityTimeEntity);
        return availabilityTimeEntity;
    }

    public AvailabilityTimeEntity update(AvailabilityTimeEntity availabilityTimeEntity){
        checkArgumentIsNotNull(availabilityTimeEntity);
        for (AvailabilityTimeEntity time : getAll()) {
            if (time.getIdTime() != null && time.getIdTime().equals(time.getIdTime())) {
                time.setAvaible(availabilityTimeEntity.isAvaible());
                time.setAvailabilityTimeFrom(availabilityTimeEntity.getAvailabilityTimeFrom());
                time.setAvailabilityTimeTo(availabilityTimeEntity.getAvailabilityTimeTo());
                time.setComment(availabilityTimeEntity.getComment());
                return time;
            }
        }
        throw new IllegalArgumentException("There is no previous game with such id");
    }
}
