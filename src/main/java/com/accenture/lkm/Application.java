package com.accenture.lkm;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	public final static String queueName = "dummy-msd-queue";
	public final static String messageKey = "spring.boot.employee.message";
	public final static String bindingPattern = "spring.#";
	//Creating Queue
	@Bean
	Queue queue() {
		System.out.println("From Application:> Queue Created");
		return new Queue(queueName, false);
	}
	// Creating Exchange
	@Bean
	TopicExchange exchange() {
		System.out.println("From Application:> Exchange Topic Created");
		return new TopicExchange("spring-boot-topic-exchange-msd");
	}
	//Binding between Exchange and Queue using binding pattern key
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		System.out.println("From Application:> Binding done");
		return BindingBuilder.bind(queue).to(exchange).with(bindingPattern);
	}

	// Used to Convert the Message Body to Bytes and Send to exchange  
	@Bean
	public JsonMessageConverter jsonConverter() {
		JsonMessageConverter jsonMessageConverter = new JsonMessageConverter();
		return jsonMessageConverter;
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
	}

}
