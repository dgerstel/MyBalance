package com.mybalance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mybalance.model.BalanceCalculation;
import com.mybalance.model.TotalBalanceCalculation;
import com.mybalance.model.User;
import com.mybalance.service.BalanceCalculationService;
import com.mybalance.service.UserService;

@Controller
@RequestMapping
public class BalanceController {
	private UserService userService;
	private BalanceCalculationService balCalcService;

	@Autowired
	public BalanceController(UserService userService, BalanceCalculationService balCalcService) {
		this.userService = userService;
		this.balCalcService = balCalcService;
	}

	@GetMapping("/balance")
	public String calculate(Model model) {

		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		User currentUser = userService.findUser(currentUserName);

		TotalBalanceCalculation totalBalCalc = balCalcService.calculateTotalBalance(currentUser);
		List<BalanceCalculation> calculations = balCalcService.calculateBalance(currentUser);

		model.addAttribute("calculations", calculations);
		model.addAttribute("totalBalCalc", totalBalCalc);
		return "balance";
	}
}