package com.accenture.lkm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.business.bean.Employee;
import com.accenture.lkm.service.EmployeeServiceImpl;

@RestController
// extends @Controller
// object are automaticaly convereted to JSON or XML
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeService;	
	
	@RequestMapping(value="/emp/controller/addEmp",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
		employeeService.addEmployee(employee);
		return new ResponseEntity<String>("Employee added successfully to the Queue",HttpStatus.CREATED);
	}
	
}
