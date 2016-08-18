package com.ibm.watson.test;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.watson.config.JavaConfig;
import com.ibm.watson.service.AlchemyDataNewsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { JavaConfig.class })
public class AlchemyDataNewsServiceIntegrationTest {

	@Autowired
	private AlchemyDataNewsService alchemyDataNewsService;

	@Test
	public void alchemyDataNewsTest() {
		LocalDate startDate=LocalDate.of(2016, Month.AUGUST, 1);
		LocalDate endDate=LocalDate.now();
		int total=5;
		String result = alchemyDataNewsService.getNewsBetween(startDate, endDate, total);
		System.out.println(result);
	}
}
