package com.weather.XmlElements;

public class Summary {

	private String type;
	private String content;

	@Override
	public String toString() {
		return getContent();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
