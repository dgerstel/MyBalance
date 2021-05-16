package com.mybalance.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mybalance.model.BalanceCalculation;
import com.mybalance.model.TotalBalanceCalculation;
import com.mybalance.model.User;
import com.mybalance.service.BalanceCalculationService;
import com.mybalance.service.UserService;

@Controller
@ComponentScan
@RequestMapping
public class SelectedDatesBalanceController {

	private UserService userService;
	private BalanceCalculationService balCalcService;

	@Autowired
	public SelectedDatesBalanceController(UserService userService, BalanceCalculationService balCalcService) {
		this.userService = userService;
		this.balCalcService = balCalcService;
	}

	@RequestMapping("/displayForSelectedDates")
	public String selectDates(@RequestParam String fromDate, @RequestParam String toDate, Model model) {

		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		User currentUser = userService.findUser(currentUserName);
		
		TotalBalanceCalculation totalBalCalc = balCalcService.calculateTotalBalanceInScope(currentUser,
				LocalDate.parse(fromDate), LocalDate.parse(toDate));
		List<BalanceCalculation> balCalc = balCalcService.calculateBalanceInScope(currentUser,
				LocalDate.parse(fromDate), LocalDate.parse(toDate));

		model.addAttribute("totalBalCalc", totalBalCalc);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("balCalc", balCalc);
		return "displayForSelectedDates";
	}
}
