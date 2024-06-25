package com.springboot.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloControlloer {
	@RequestMapping("haloo")
	@ResponseBody
	public String sayHello() {
		return "Hello";
	}
	
	
	@RequestMapping("hello-jsp")
	public String sayHelloJSP() {
		return "hello";
	}
}
