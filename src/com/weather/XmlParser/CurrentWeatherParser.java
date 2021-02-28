package com.weather.XmlParser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.weather.XmlElements.Entry;

public class CurrentWeatherParser {
	Logger logger = LoggerFactory.getLogger(CurrentWeatherParser.class);
	private final String url;
	private String summary;
	private Weather weather;

	public WeatherParser parseWebsite() throws SAXException, ParserConfigurationException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		WeatherParser weatherParser = new WeatherParser();
		saxParser.parse(url, weatherParser);
		return weatherParser;

	}

	public Entry extractEntryByCatagory(String catagory) throws Exception {
		WeatherParser weatherParser = parseWebsite();
		for (Entry entry : weatherParser.getTempFeed().getEntries()) {
			if (catagory.equals(entry.getCatagory().toString())) {
				return entry;
			}
		}
		throw new Exception("No matched entry for this catagory was found");
	}

	public String extractSummaryOfCurrentConditions() throws Exception {
		Entry entry = extractEntryByCatagory("Current Conditions");
		if (entry != null) {
			return entry.getSummary().getContent();
		} else {
			throw new Exception("Error in getting summary of current conditions");
		}

	}

	public void extractCurrentWeather() throws Exception {
		this.setSummary(extractSummaryOfCurrentConditions());
		
		String localSummary = this.getSummary();
		localSummary = localSummary.substring(localSummary.indexOf("<b>Humidity:</b>"));
		String humidity = localSummary.substring(localSummary.indexOf("</b>") + 5, localSummary.indexOf("%<br/>"));
		this.getWeather().setHumidity(humidity);

		localSummary = this.getSummary();
		localSummary = localSummary.substring(localSummary.indexOf("<b>Pressure / Tendency:</b>"));
		String pressure = localSummary.substring(localSummary.indexOf("</b>") + 5, localSummary.indexOf("kPa"));
		this.getWeather().setPressure(pressure);

		localSummary = this.getSummary();
		localSummary = localSummary.substring(localSummary.indexOf("<b>Temperature:</b>"));
		String tempreture = localSummary.substring(localSummary.indexOf("</b>") + 5, localSummary.indexOf("&"));
		this.getWeather().setTempreture(tempreture);
	}

	public String extractCurrentHumidity() throws Exception {
		return "H:" + this.getWeather().getHumidity();
	}

	public String extractCurrentPressure() throws Exception {
		return "P:" + this.getWeather().getPressure();
	}

	public String extractCurrentTemperature() throws Exception {
		return "T:" + this.getWeather().getTempreture();
	}

	public CurrentWeatherParser() {
		this.weather = new Weather();
		this.url = "https://meteo.gc.ca/rss/city/qc-147_e.xml";
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}