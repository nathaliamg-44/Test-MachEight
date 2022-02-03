package com.macheight.test;

import java.util.HashSet;
import java.util.stream.Collectors;

public class Pair {

	private HashSet<Player> players;

	public Pair(Player player1, Player player2) {
		players = new HashSet<>();
		players.add(player1);
		players.add(player2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return players.stream().map(player -> player.getFirst_name() + " " + player.getLast_name())
				.collect(Collectors.joining("    "));
	}

}
