package com.weather.XmlParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weather.threads.PublisherThread;

public class ContinuousWeatherParser {
Logger logger=LoggerFactory.getLogger("ContinuousWeatherParser");
	private CurrentWeatherParser weatherOfMontreal = new CurrentWeatherParser();

	public void publishUpdatedWeatherConditionsToBroker() throws Exception {
		String currentPressure;
		String currentHumidity;
		String currentTempreture;
		String oldPressure;
		String oldHumidity;
		String oldTempreture;
		weatherOfMontreal.extractCurrentWeather();
		currentHumidity = weatherOfMontreal.extractCurrentHumidity();
		PublisherThread humidityPublisherThread = new PublisherThread(currentHumidity, "humidityPublisher");
		humidityPublisherThread.start();

		currentPressure = weatherOfMontreal.extractCurrentPressure();
		PublisherThread pressurePublisherThread = new PublisherThread(currentPressure, "pressurePublisher");
		pressurePublisherThread.start();

		currentTempreture = weatherOfMontreal.extractCurrentTemperature();
		PublisherThread tempreturePublisherThread = new PublisherThread(currentTempreture, "tempreturePublisher");
		tempreturePublisherThread.start();

		while (true) {
			oldPressure = currentPressure;
			oldHumidity = currentHumidity;
			oldTempreture = currentTempreture;
			weatherOfMontreal.extractCurrentWeather();
			currentHumidity = weatherOfMontreal.extractCurrentHumidity();
			currentPressure = weatherOfMontreal.extractCurrentPressure();
			currentTempreture = weatherOfMontreal.extractCurrentTemperature();
			System.out.println(currentPressure);
			System.out.println(currentHumidity);
			System.out.println(currentTempreture + "\n");

			if (currentHumidity.equals(oldHumidity) == false) {
				logger.info("Humidity got updated");
				PublisherThread publisherThread = new PublisherThread(currentHumidity, "humidity-publisher");
				publisherThread.start();
			}

			if (currentPressure.equals(oldPressure) == false) {
				logger.info("Pressure got updated");
				PublisherThread publisherThread = new PublisherThread(currentPressure, "pressure-publisher");
				publisherThread.start();
			}

			if (currentTempreture.equals(oldTempreture) == false) {
				logger.info("Tempreture got updated");
				PublisherThread publisherThread = new PublisherThread(currentTempreture, "tempreture-publisher");
				publisherThread.start();
			}
			Thread.sleep(5000);

		}

	}
}
