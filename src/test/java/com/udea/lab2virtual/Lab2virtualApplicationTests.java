package com.udea.lab2virtual;

import com.fasterxml.jackson.databind.JsonNode;
import com.udea.lab2virtual.controller.DataController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Lab2virtualApplicationTests {

	@Autowired
	DataController dataController;

	@Test
	void health(){
		assertEquals("HEALTH CHECK OK!", dataController.healthcheck());
	}

	@Test
	void version(){
		assertEquals("The actual version is 0.0.7", dataController.version());
	}

	@Test
	void nationLength(){
		Integer nationLength = dataController.getRandomNations().size();
		assertEquals(10, nationLength);
	}

	@Test
	void currenciesLength(){
		Integer currenciesLength = dataController.getRandomCurrencies().size();
		assertEquals(10, currenciesLength);
	}

	@Test
	public void testRandomCurrenciesCodeFormal(){
		DataController controller = new DataController();
		JsonNode response = controller.getRandomCurrencies();
		for (int i=0; i< response.size(); i++) {
			JsonNode currency = response.get(i);
			String code = currency.get("code").asText();
			assertTrue(code.matches("[A-Z]{3}"));
		}
	}

	@Test
	public void testRandomNationsPerformance(){
		DataController controller = new DataController();
		Long startTime = System.currentTimeMillis();
		controller.getRandomNations();
		Long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		System.out.println(executionTime);
		assertTrue(executionTime < 2000);
	}

	@Test
	void aviationLength(){
		Integer aviationLength = dataController.getRandomAviation().size();
		assertEquals(10, aviationLength);
	}

}
