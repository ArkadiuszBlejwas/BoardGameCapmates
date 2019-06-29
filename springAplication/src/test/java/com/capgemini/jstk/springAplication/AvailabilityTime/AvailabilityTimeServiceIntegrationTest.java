package com.capgemini.jstk.springAplication.AvailabilityTime;

import com.capgemini.jstk.springAplication.Aplication.SpringAplication;
import com.capgemini.jstk.springAplication.User.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAplication.class)
public class AvailabilityTimeServiceIntegrationTest {

    @Autowired
    AvailabilityTimeService availabilityTimeService;

    @Autowired
    UserService userService;

    @Before
    public void callSetup(){
        availabilityTimeService.callSetup();
        userService.callSetup();
    }

    @Test
    public void shouldAddAvailabilityTime(){
        AvailabilityTimeDTO time = new AvailabilityTimeDTO.AvailabilityTimeDTOBuilder()
                .idTime(0L)
                .availabilityTimeFrom(LocalDateTime.of(2018, Month.APRIL, 1, 20, 0))
                .availabilityTimeTo(LocalDateTime.of(2018, Month.APRIL, 1, 21, 0))
                .isAvaible(true)
                .comment("Cip cip cip")
                .build();
        availabilityTimeService.addAvailabilityTime(0L, time);
        assertEquals(time, userService.getUserById(0L).getAvailabilityTime().get(0));
    }


    @Test
    public void shouldUpdateAvailabilityTime(){
        AvailabilityTimeDTO time = new AvailabilityTimeDTO.AvailabilityTimeDTOBuilder()
                .idTime(0L)
                .availabilityTimeFrom(LocalDateTime.of(2018, Month.APRIL, 1, 20, 0))
                .availabilityTimeTo(LocalDateTime.of(2018, Month.APRIL, 1, 21, 0))
                .isAvaible(true)
                .comment("Mama mi pozwala")
                .build();
        availabilityTimeService.addAvailabilityTime(2L, time);
        AvailabilityTimeDTO time2 = new AvailabilityTimeDTO.AvailabilityTimeDTOBuilder()
                .idTime(0L)
                .availabilityTimeFrom(LocalDateTime.of(2018, Month.APRIL, 1, 20, 30))
                .availabilityTimeTo(LocalDateTime.of(2018, Month.APRIL, 1, 21, 30))
                .isAvaible(true)
                .comment("xD xD xD")
                .build();
        availabilityTimeService.updateAvailabilityTime(2L, time2);
        assertEquals(
                LocalDateTime.of(2018, Month.APRIL, 1, 20, 30),
                userService.getUserById(2L).getAvailabilityTime().get(0).getAvailabilityTimeFrom());
    }

    @Test
    public void shouldRemoveAvailabilityTime(){
        AvailabilityTimeDTO time = new AvailabilityTimeDTO.AvailabilityTimeDTOBuilder()
                .idTime(0L)
                .availabilityTimeFrom(LocalDateTime.of(2018, Month.APRIL, 1, 18, 0))
                .availabilityTimeTo(LocalDateTime.of(2018, Month.APRIL, 1, 19, 0))
                .isAvaible(true)
                .comment("Ble ble ble")
                .build();
        availabilityTimeService.addAvailabilityTime(0L, time);
        availabilityTimeService.removeAvailabilityTime(0L, 0L, "Sory chopaki ale mama mi zabrania :(");
        assertEquals(
                "Sory chopaki ale mama mi zabrania :(",
                userService.getUserById(0L).getAvailabilityTime().get(0).getComment());
        assertEquals(false, userService.getUserById(0L).getAvailabilityTime().get(0).isAvaible());
    }

    @Test
    public void shouldThrowsExceptionWhenAddAvailabilityTimeWhichCoverageWithOtherAvailabilityTime(){
        AvailabilityTimeDTO time = new AvailabilityTimeDTO.AvailabilityTimeDTOBuilder()
                .idTime(0L)
                .availabilityTimeFrom(LocalDateTime.of(2018, Month.APRIL, 1, 20, 0))
                .availabilityTimeTo(LocalDateTime.of(2018, Month.APRIL, 1, 21, 0))
                .isAvaible(true)
                .comment("Cip cip cip")
                .build();
        availabilityTimeService.addAvailabilityTime(0L, time);
        AvailabilityTimeDTO time2 = new AvailabilityTimeDTO.AvailabilityTimeDTOBuilder()
                .idTime(1L)
                .availabilityTimeFrom(LocalDateTime.of(2018, Month.APRIL, 1, 20, 0))
                .availabilityTimeTo(LocalDateTime.of(2018, Month.APRIL, 1, 21, 0))
                .isAvaible(true)
                .comment("Cip cip cip")
                .build();
        availabilityTimeService.addAvailabilityTime(0L, time2);
    }

    @Test
    public void shouldAddAvailabilityTimeToSystem(){
        AvailabilityTimeDTO time = new AvailabilityTimeDTO.AvailabilityTimeDTOBuilder()
                .idTime(0L)
                .availabilityTimeFrom(LocalDateTime.of(2018, Month.APRIL, 1, 20, 0))
                .availabilityTimeTo(LocalDateTime.of(2018, Month.APRIL, 1, 21, 0))
                .isAvaible(true)
                .comment("Cip cip cip")
                .build();
        availabilityTimeService.addAvailabilityTimeToSystem(time);
        assertEquals(time, availabilityTimeService.getAvailabilityTimeById(0L));
    }

}