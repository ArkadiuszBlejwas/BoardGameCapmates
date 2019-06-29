package com.capgemini.jstk.springAplication.AvailabilityTime;

import com.capgemini.jstk.springAplication.Interfaces.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvailabilityTimeMapper implements Mapper<AvailabilityTimeEntity, AvailabilityTimeDTO> {

    public AvailabilityTimeDTO convertToDto(AvailabilityTimeEntity availabilityTimeEntity){
        if (availabilityTimeEntity == null){
            return null;
        }
        return new AvailabilityTimeDTO(
                availabilityTimeEntity.getIdTime(),
                availabilityTimeEntity.getAvailabilityTimeFrom(),
                availabilityTimeEntity.getAvailabilityTimeTo(),
                availabilityTimeEntity.isAvaible(),
                availabilityTimeEntity.getComment());
    }

    public AvailabilityTimeEntity convertToEntity(AvailabilityTimeDTO availabilityTimeDTO){
        if (availabilityTimeDTO == null){
            return null;
        }
        return new AvailabilityTimeEntity(
                availabilityTimeDTO.getIdTime(),
                availabilityTimeDTO.getAvailabilityTimeFrom(),
                availabilityTimeDTO.getAvailabilityTimeTo(),
                availabilityTimeDTO.isAvaible(),
                availabilityTimeDTO.getComment());
    }

    public List<AvailabilityTimeDTO> convertToDtoList(List<AvailabilityTimeEntity> availabilityTimeEntityList){
        return availabilityTimeEntityList.stream()
                .map(time -> new AvailabilityTimeDTO(
                        time.getIdTime(),
                        time.getAvailabilityTimeFrom(),
                        time.getAvailabilityTimeTo(),
                        time.isAvaible(),
                        time.getComment()))
                .collect(Collectors.toList());
    }

    public List<AvailabilityTimeEntity> convertToEntityList(List<AvailabilityTimeDTO> availabilityTimeDTOList){
        return availabilityTimeDTOList.stream()
                .map(time -> new AvailabilityTimeEntity(
                        time.getIdTime(),
                        time.getAvailabilityTimeFrom(),
                        time.getAvailabilityTimeTo(),
                        time.isAvaible(),
                        time.getComment()))
                .collect(Collectors.toList());
    }
}
