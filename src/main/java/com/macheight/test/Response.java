package com.macheight.test;

import java.util.List;

public class Response {

	private List<Player> values;

	public List<Player> getValues() {
		return values;
	}

	public void setValues(List<Player> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "Response [values=" + values + "]";
	}
}
