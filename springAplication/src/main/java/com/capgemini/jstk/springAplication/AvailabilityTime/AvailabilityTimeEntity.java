package com.capgemini.jstk.springAplication.AvailabilityTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class AvailabilityTimeEntity {

    private Long idTime;
    private LocalDateTime availabilityTimeFrom;
    private LocalDateTime availabilityTimeTo;
    private boolean isAvaible;
    private String comment;

}
