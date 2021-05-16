package com.mybalance.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ComponentScan
@RequestMapping
public class InfoController {
	@RequestMapping("/info")
	public String home() {
		return "info";
	}
}