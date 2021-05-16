package com.mybalance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mybalance.model.Activity;
import com.mybalance.model.ActivityDiary;
//import com.mybalance.model.ActivityCategory;
//import com.mybalance.model.Date;
//import com.mybalance.model.Display;
import com.mybalance.model.FoodDiary;
import com.mybalance.model.Meal;
//import com.mybalance.model.MealCategory;
import com.mybalance.model.User;
import com.mybalance.service.DatesService;
import com.mybalance.service.DeleteDiaryService;
import com.mybalance.service.DisplayDiaryService;
import com.mybalance.service.FindInDataBaseService;
import com.mybalance.service.UserService;

@Controller
@ComponentScan
@RequestMapping
public class AdminPageController {

	private UserService userService;
	private DisplayDiaryService displayDiaryService;
	private FindInDataBaseService findInDataBaseService;
	private DeleteDiaryService delDiaryService;

	@Autowired
	public AdminPageController(UserService userService, DisplayDiaryService displayDiaryService,
			DatesService datesService, FindInDataBaseService findInDataBaseService,
			DeleteDiaryService delDiaryService) {
		this.userService = userService;
		this.displayDiaryService = displayDiaryService;
		this.findInDataBaseService = findInDataBaseService;
		this.delDiaryService = delDiaryService;

	}

	@RequestMapping("/adminPage/")
	public String showAll(Model model) {
		Iterable<User> allUsers = userService.displayUser();
		Iterable<Meal> allMeals = findInDataBaseService.allMeals();
		Iterable<Activity> allActs = findInDataBaseService.allActs();
		String countAll = displayDiaryService.getStatistics();

		model.addAttribute("allMeals", allMeals);
		model.addAttribute("allActs", allActs);
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("countAll", countAll);
		return "adminPage";
	}

	@PostMapping("/deleteUser")
	public String deleteUser(Model model, @RequestParam Integer id) {
		userService.deleteUser(id);
		showAll(model);
		return "adminPage";
	}

	@PostMapping("/deleteMeal")
	public String deleteMeal(Model model, @RequestParam Integer id) {
		delDiaryService.deleteMeal(id);
		showAll(model);
		return "adminPage";
	}

	@PostMapping("/deleteAct")
	public String deleteAct(Model model, @RequestParam Integer id) {
		delDiaryService.deleteActivity(id);
		showAll(model);
		return "adminPage";
	}

	@RequestMapping("/adminPage/page2")
	public String showAllDiary(Model model) {

		Iterable<FoodDiary> allFoodDiary = displayDiaryService.displayFoodDiary();
		Iterable<ActivityDiary> allActDiary = displayDiaryService.displayActDiary();

		model.addAttribute("allFoodDiary", allFoodDiary);
		model.addAttribute("allActDiary", allActDiary);
		return "adminPage2";
	}

	@PostMapping("/deleteFromFoodDiary")
	public String foodDiaryToDelete(Model model, @RequestParam Integer id) {

		delDiaryService.deleteFoodDiary(id);
		showAll(model);
		return "adminPage2";
	}

	@PostMapping("/deleteFromActDiary")
	public String actDiaryToDelete(Model model, @RequestParam Integer id) {

		delDiaryService.deleteActivityDiary(id);
		showAll(model);
		return "adminPage2";
	}

}