package com.accenture.lkm.service;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.Application;
import com.accenture.lkm.business.bean.Employee;
import com.accenture.lkm.business.bean.MessageBean;

@Service
public class EmployeeServiceImpl {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	TopicExchange ex;
	
	@Autowired
	private JsonMessageConverter jsonConv;
	
	public void addEmployee(Employee employee) {
		MessageBean bean = new MessageBean();
		bean.setEmployee(employee);
		bean.setMessage("This Employee is new Employee");
		rabbitTemplate.setMessageConverter(jsonConv);
		//Sending to topic with a messageKey
		rabbitTemplate.convertAndSend(ex.getName(), Application.messageKey, bean);
		System.out.println("Message is sent check the Queue in Rabbit MQ...");
	}

}
