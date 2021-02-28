package com.weather.threads;

import com.weather.publishingMessage.Publisher;

public class PublisherThread extends Thread {
	String messageToPublish;
	String clientId;

	@Override
	public void run() {
		Publisher publishingATopicToBroker = new Publisher();
		try {
			publishingATopicToBroker.publishATopicToBrokerUsingMqtt(messageToPublish, this.clientId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PublisherThread(String message, String clientId) {
		messageToPublish = message;
		this.clientId = clientId;
	}
}
