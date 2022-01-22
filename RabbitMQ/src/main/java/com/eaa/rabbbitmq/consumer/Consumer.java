package com.eaa.rabbbitmq.consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

// Consumer the message from Queue
public class Consumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		DeliverCallback deliverCallback = (ConsumerTag, delivery ) ->{
			String message = new String(delivery.getBody());
			System.out.println("Message Received "+message);
		};
		
		//channel.basicConsume("Queue-1", true, deliverCallback, conumerTag -> {});
		
		//channel.basicConsume("Mobile", true, deliverCallback, conumerTag -> {});
		
		channel.basicConsume("TV", true, deliverCallback, conumerTag -> {});

	}

}
