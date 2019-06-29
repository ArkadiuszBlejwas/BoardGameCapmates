package com.capgemini.jstk.springAplication.AvailabilityTime;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityTimeDTO {

    @EqualsAndHashCode.Exclude
    private Long idTime;
    private LocalDateTime availabilityTimeFrom;
    private LocalDateTime availabilityTimeTo;
    private boolean isAvaible;
    private String comment;
}
