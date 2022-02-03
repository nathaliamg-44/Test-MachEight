package com.macheight.test;

import java.util.HashSet;
import java.util.Set;

public class Execution {

	private int number;

	private long duration;

	private Set<Pair> results;

	private long operations;

	public Execution() {
		results = new HashSet<>();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public Set<Pair> getResults() {
		return results;
	}

	public void setResults(Set<Pair> results) {
		this.results = results;
	}

	public long getOperations() {
		return operations;
	}

	public void setOperations(long operations) {
		this.operations = operations;
	}

	@Override
	public String toString() {
		return "Execution [duration=" + duration + ", number=" + number + ", operations=" + operations
				+ ", results=" + results.size() + "]";
	}

	public void add(Execution execution) {
		if (this.number != execution.number) {
			throw new RuntimeException("Executions can not be added. Different numbers.");
		}
		if (this.results.size() != execution.results.size()) {
			throw new RuntimeException("Executions can not be added. Different results.");
		}
		this.duration += execution.duration;
		this.operations += execution.operations;
	}

	public void divide(int tests) {
		this.duration /= tests;
		this.operations /= tests;
	}

}
