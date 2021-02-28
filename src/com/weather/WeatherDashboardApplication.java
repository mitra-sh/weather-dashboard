/*
I certify that this assignment/report is my own work, based on my personal study and/or research. I also certify
that I have not copied in part or whole or otherwise plagiarized the work of other students and/or persons. I also
certify that I will not share my solution with others nor make it publicly available online..
*/
package com.weather;

import com.weather.XmlParser.ContinuousWeatherParser;

public class WeatherDashboardApplication {

	public static void main(String[] args) throws Exception {
		ContinuousWeatherParser continuousWeatherParser = new ContinuousWeatherParser();
		continuousWeatherParser.publishUpdatedWeatherConditionsToBroker();
	}

}
