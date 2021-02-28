
package com.weather.publishingMessage;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Publisher {
	Logger logger = LoggerFactory.getLogger(Publisher.class);

	public void publishATopicToBrokerUsingMqtt(String message, String clientId) {
		final String topic = "weather-dfe56c74-82c0-4461-8245-4e6760b71940";
		final String broker = "tcp://broker.mqttdashboard.com:1883";
		try {
			MqttClient publisher = new MqttClient(broker, clientId);
			MqttConnectOptions connetionOptions = new MqttConnectOptions();
			connetionOptions.setCleanSession(true);
			logger.info(("Connecting to broker: " + broker));
			publisher.connect(connetionOptions);
			logger.info("Connected to broker");
			MqttMessage finalMessageToSend = new MqttMessage(message.getBytes());
			publisher.publish(topic, finalMessageToSend);
			logger.info("Message published");
			publisher.disconnect();
			logger.info("Disconnected from broker");

		} catch (MqttException me) {
			logger.error("reason: " + me.getReasonCode());
			logger.error("error message: " + me.getMessage());
			logger.error("cause: " + me.getCause());
			logger.error("exception: " + me);
			me.printStackTrace();
		}
	}

}
