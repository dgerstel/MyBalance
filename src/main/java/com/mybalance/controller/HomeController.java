package com.mybalance.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ComponentScan
@RequestMapping
public class HomeController {
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/logmeout")
	public String logout() {
		return "index";
	}
}