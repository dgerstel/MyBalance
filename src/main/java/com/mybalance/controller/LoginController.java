package com.mybalance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mybalance.security.CustomUserDetailsService;

@Controller
public class LoginController {

	CustomUserDetailsService customUserDetailsService;

	@GetMapping("/login")
	public String loginForm() {
		return "login_form";
	}
}
