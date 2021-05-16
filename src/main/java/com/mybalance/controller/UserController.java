package com.mybalance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mybalance.model.User;
import com.mybalance.service.UserService;

@Controller
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}

	@PostMapping("/register")
	public String addUser(@ModelAttribute @Valid User user, BindingResult bindResult) {

		if (userExist(user.getUserName())) {
			bindResult.rejectValue("userName", null, "Użytkownik o podanej nazwie już istnieje!");
		}
		if (bindResult.hasErrors())
			return "signup_form";
		else {
			userService.addWithDefaultRole(user);
			return "index";
		}
	}

	private boolean userExist(String userName) {
		return userService.findUser(userName) != null;
	}

}