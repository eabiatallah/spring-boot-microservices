package com.eaa.rabbbitmq.publihser;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DirectPublisher {
	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

//		String message = "This is Mobile !";
//		channel.basicPublish("Direct-Exchange", "Mobile", null, message.getBytes());
		
		String message = "This is AC !";
		channel.basicPublish("Direct-Exchange", "AC", null, message.getBytes());

		channel.close();
		connection.close();
	}

}
