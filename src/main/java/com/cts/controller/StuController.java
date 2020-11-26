package com.cts.controller;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.controller.model.Students;

@RestController
public class StuController {
	
	@Autowired
	CamelContext camelContext;
	
	@Autowired
	ProducerTemplate producerTemplate;

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public List<Students> getAllStudents() {
		List<Students> students = producerTemplate.requestBody("direct:select", null, List.class);
		return students;

	}

	@RequestMapping(value = "/students", consumes = "application/json", method = RequestMethod.POST)
	public boolean insertEmployee(@RequestBody Students stu) {
		producerTemplate.requestBody("direct:insert", stu, List.class);
		return true;
	}

}