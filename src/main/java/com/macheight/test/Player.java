package com.macheight.test;

public class Player {

	private String first_name;
	private String last_name;
	private int h_in;
	private double h_meters;

	public Player() {
	}

	public Player(String first_name, String last_name, int h_in, double h_meters) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.h_in = h_in;
		this.h_meters = h_meters;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getH_in() {
		return h_in;
	}

	public void setH_in(int h_in) {
		this.h_in = h_in;
	}

	public double getH_meters() {
		return h_meters;
	}

	public void setH_meters(double h_meters) {
		this.h_meters = h_meters;
	}

	@Override
	public String toString() {
		return "Jugador [first_name=" + first_name + ", h_in=" + h_in + ", h_meters=" + h_meters + ", last_name="
				+ last_name + "]";
	}
}
