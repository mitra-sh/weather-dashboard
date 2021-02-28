package com.weather;

import com.weather.XmlParser.ContinuousWeatherParser;

public class WeatherDashboardApplication {

	public static void main(String[] args) throws Exception {
		ContinuousWeatherParser continuousWeatherParser = new ContinuousWeatherParser();
		continuousWeatherParser.publishUpdatedWeatherConditionsToBroker();
	}

}
