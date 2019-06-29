package com.capgemini.jstk.springAplication;

import com.capgemini.jstk.springAplication.AvailabilityTime.AvailabilityTimeServiceIntegrationTest;
import com.capgemini.jstk.springAplication.Game.GameMapperTest;
import com.capgemini.jstk.springAplication.Game.GameServiceIntegrationTest;
import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameMapperTest;
import com.capgemini.jstk.springAplication.PreviousGame.PreviousGameServiceIntegrationTest;
import com.capgemini.jstk.springAplication.User.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AvailabilityTimeServiceIntegrationTest.class,
        GameServiceIntegrationTest.class,
        UserMapperTest.class,
        UserServiceTest.class,
        UserServiceIntegrationTest.class,
        PreviousGameServiceIntegrationTest.class,
        PreviousGameMapperTest.class,
        GameMapperTest.class,
        UserAPITest.class
})
public class SuiteClassTest {
}
