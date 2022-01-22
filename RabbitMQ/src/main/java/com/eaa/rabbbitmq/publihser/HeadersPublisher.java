package com.eaa.rabbbitmq.publihser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HeadersPublisher {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		String message = "Message for Mobile and TV";
		
		Map<String, Object> amap = new HashMap<>();
		amap.put("item1", "mobile");
		amap.put("item2", "television");
		
		BasicProperties br = new BasicProperties();
		br = br.builder().headers(amap).build();
		
		channel.basicPublish("Headers-Exchange", "", br, message.getBytes()); // routing cannot be null always "" if no values

		channel.close();
		connection.close();
	}

}
