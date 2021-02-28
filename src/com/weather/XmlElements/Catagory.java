package com.weather.XmlElements;

public class Catagory {
	private String term;

	@Override
	public String toString() {
		return getTerm();
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
}
