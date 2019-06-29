package com.capgemini.jstk.springAplication;

import com.capgemini.jstk.springAplication.Aplication.SpringAplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAplication.class)
public class SpringApplicationTests {

	@Test
	public void contextLoads() {
	}

}
