package com.mybalance.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mybalance.model.User;
import com.mybalance.service.UserService;

@Controller
@ComponentScan
@RequestMapping
public class UserInfoController {

	private UserService userService;

	@Autowired
	public UserInfoController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/userInfo")
	public String displayUserInfo(Model model) {
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		User currentUser = userService.findUser(currentUserName);

		model.addAttribute("currentUser", currentUser);
		return "userInfo";
	}

	@PostMapping("/changeUserInfo")
	public String updateUser(@ModelAttribute @Valid User user, BindingResult bindResult, Model model) {

		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

		if (bindResult.hasErrors()) {
			String errorMessage = bindResult.getFieldErrors().stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList())
					.toString();

			model.addAttribute("error", errorMessage);
			displayUserInfo(model);
			return "userInfo";
		}
		User currentUser = userService.findUser(currentUserName);
		userService.updateUser(currentUser.getId(), user);
		displayUserInfo(model);
		return "userInfo";
	}
}
