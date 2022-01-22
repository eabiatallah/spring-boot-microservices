package com.eaa.rabbbitmq.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eaa.rabbbitmq.model.Person;

@RestController
@RequestMapping("/api/v1")
public class TestController {
	
	@Autowired
	RabbitTemplate rabbitTemplate; // public message to queue

	/*@GetMapping("/test/{name}")
	public String testAPI(@PathVariable("name") String name) {
		Person p = new Person(1L, name);
		//rabbitTemplate.convertAndSend("Mobile", p); // We should implements Serializable otherwise this will fail
		//rabbitTemplate.convertAndSend("Direct-Exchange", "Mobile", p);
		rabbitTemplate.convertAndSend("Fanout-Exchange", "", p);
		//rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", p);
		 
		 
		return "success";
	}
	*/
	@GetMapping("/test2/{name}")
	public String testHeaderExchange(@PathVariable("name") String name) throws IOException {
		Person p = new Person(1L, name);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(p);
		out.flush();
		out.close();
		
		byte[] byteMessage = bos.toByteArray();
		bos.close();
		
		Message message = MessageBuilder.withBody(byteMessage)
				.setHeader("item1", "mobile")
				.setHeader("item2", "television").build();
		
		rabbitTemplate.send("Headers-Exchange", "", message);
		
		return "Success";
	}
	
	@GetMapping("/defaultExchange/{name}")
	public String defaultExchange (@PathVariable String name) {
		Person p = new Person(1L, name);
		rabbitTemplate.convertAndSend("Mobile", p);
		// Or:  rabbitTemplate.convertAndSend("", "Mobile", p); // Default-Exchange
		
		return "Success";
	}



}
