package com.macheight.test;

import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;

public class Application {

	public static final String MACH_EIGHT_URL = "https://mach-eight.uc.r.appspot.com/";
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			ObjectMapper mapper = new ObjectMapper();
			Response response = mapper.readValue(new URL(MACH_EIGHT_URL), Response.class);
			LOG.info("Enter a number");
			int num = scanner.nextInt();
			Execution execution = pairs(response.getValues(), num);
			LOG.info("Result: {}", execution);
			if (execution.getResults().isEmpty()) {
				LOG.info("No matches found");
			}
			for (Pair couple : execution.getResults()) {
				LOG.info(couple.toString());
			}
		} catch (Exception e) {
			LOG.error("Error", e);
		}
	}

	/*
	 * Optimal algorithm
	 * This algorithm find the pairs must be faster than O(n^2), estimated as
	 * O(n + k^2), where k is the number of players with the same key. The
	 * performance of the algorithm dependes on the "entropy" of the data, because
	 * with a low k the performance is really good and with a higher k the
	 * performance is degraded. Thus it is more efficient in the average case.
	 * 
	 * The first step is to build a MultiValueMap where the players are stored in
	 * different lists according to its height in inches.
	 * 
	 * For every height key in the map the algorithm generates the pairs using all
	 * the players in that key with all the players in the complement key.
	 * If the key is equal to the complement key, the algorithm generates the pairs
	 * using each player in the key with the subsequent players in that key.
	 */
	public static Execution pairs(List<Player> players, int num) {
		Execution execution = new Execution();
		long o = 0;
		long t = System.nanoTime();
		MultiValueMap<Integer, Player> map = new MultiValueMap<>();
		for (int i = 0; i < players.size(); i++) {
			map.add(players.get(i).getH_in(), players.get(i));
			o += 4;
		}
		while (!map.isEmpty()) {
			int key = new ArrayList<>(map.keySet()).get(0);
			int key2 = num - key;
			o++;
			List<Player> list1 = map.get(key);
			List<Player> list2 = map.get(key2);
			if (list2 != null) {
				for (int i = 0; i < list1.size(); i++) {
					Player player1 = list1.get(i);
					int j = 0;
					if (key == key2) {
						j = i + 1;
					}
					for (; j < list2.size(); j++) {
						Player player2 = list2.get(j);
						if (player1 != player2) {
							execution.getResults().add(new Pair(player1, player2));
						}
						o++;
					}
				}
				map.remove(key2);
			}
			map.remove(key);
		}
		execution.setDuration(System.nanoTime() - t);
		execution.setNumber(num);
		execution.setOperations(o);
		return execution;
	}

	/*
	 * Sub Optimal algorithm O(n^2), used to compare execution results against
	 * optimal algorithm.
	 * 
	 * This algorithm iterates over all the players in the list and compares each
	 * one against all the subsequents players.
	 */
	public static Execution pairsOn2(List<Player> players, int num) {
		Execution execution = new Execution();
		long o = 0;
		long t = System.nanoTime();
		for (int i = 0; i < players.size(); i++) {
			for (int j = i + 1; j < players.size(); j++) {
				Player player1 = players.get(i);
				Player player2 = players.get(j);
				if (player1 != player2 && player1.getH_in() + player2.getH_in() == num) {
					execution.getResults().add(new Pair(player1, player2));
				}
				o++;
			}
		}
		execution.setDuration(System.nanoTime() - t);
		execution.setNumber(num);
		execution.setOperations(o);
		return execution;
	}
}
