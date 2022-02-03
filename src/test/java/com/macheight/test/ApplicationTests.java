package com.macheight.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

class ApplicationTests {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationTests.class);

	@Test
	void test1() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Response response = mapper.readValue(new URL(Application.MACH_EIGHT_URL), Response.class);
		int num = 139;
		Execution execution = Application.pairs(response.getValues(), num);
		assertNotNull(execution);
		assertEquals(num, execution.getNumber());
		assertEquals(2, execution.getResults().size());
	}

	@Test
	void test2() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Response response = mapper.readValue(new URL(Application.MACH_EIGHT_URL), Response.class);
		int num = 140;
		Execution execution = Application.pairs(response.getValues(), num);
		assertNotNull(execution);
		assertEquals(num, execution.getNumber());
		assertEquals(3, execution.getResults().size());
	}

	@Test
	void test3() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Response response = mapper.readValue(new URL(Application.MACH_EIGHT_URL), Response.class);
		int num = 159;
		Execution execution = Application.pairs(response.getValues(), num);
		assertNotNull(execution);
		assertEquals(num, execution.getNumber());
		assertEquals(6965, execution.getResults().size());
	}

	@Test
	void test4() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Response response = mapper.readValue(new URL(Application.MACH_EIGHT_URL), Response.class);
		System.out.println(response.getValues().size() + " players");
		int tests = 100;
		for (int i = 138; i < 177; i++) {
			List<Execution> optimalExecutions = new ArrayList<>(tests);
			List<Execution> subOptimalExecutions = new ArrayList<>(tests);
			for (int j = 0; j < tests; j++) {
				optimalExecutions.add(Application.pairs(new ArrayList<>(response.getValues()), i));
				subOptimalExecutions.add(Application.pairsOn2(new ArrayList<>(response.getValues()), i));
			}
			Execution optimalExecution = optimalExecutions.get(0);
			Execution subOptimalExecution = subOptimalExecutions.get(0);
			for (int j = 1; j < tests; j++) {
				optimalExecution.add(optimalExecutions.get(j));
				subOptimalExecution.add(subOptimalExecutions.get(j));
			}
			optimalExecution.divide(tests);
			subOptimalExecution.divide(tests);
			LOG.info("Optimal: {}", optimalExecution);
			LOG.info("SubOptimal: {}", subOptimalExecution);
			assertEquals(i, optimalExecution.getNumber());
			assertEquals(i, subOptimalExecution.getNumber());
			assertEquals(optimalExecution.getResults().size(), subOptimalExecution.getResults().size());
			assertEquals(optimalExecution.getResults(), subOptimalExecution.getResults());
		}
	}
}
