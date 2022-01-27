package com.eaa.rabbbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 

@Configuration
public class MessagingConfig {

	public static final String QUEUE = "Mobile";
	public static final String DIRECT_EXCHANGE = "Direct-Exchange";

	@Bean
	public Queue queue() {
		return new Queue(QUEUE);
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(DIRECT_EXCHANGE);
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}

}
