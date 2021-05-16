package com.mybalance.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mybalance.model.Activity;
import com.mybalance.model.ActivityCategory;
import com.mybalance.model.Meal;
import com.mybalance.model.MealCategory;
import com.mybalance.service.AddToDataBaseService;
import com.mybalance.service.FindInDataBaseService;

@Controller
@ComponentScan
@RequestMapping
public class AddToDataBaseController {

	private FindInDataBaseService findInDataBaseService;
	private AddToDataBaseService addToDataBaseService;

	@Autowired
	public AddToDataBaseController(FindInDataBaseService findInDataBaseService,
			AddToDataBaseService addToDataBaseService) {
		super();
		this.findInDataBaseService = findInDataBaseService;
		this.addToDataBaseService = addToDataBaseService;
	}

	@RequestMapping("/addToDataBase")
	public String addToDB(Model model) {

		Iterable<MealCategory> allMealCategories = findInDataBaseService.allMealCat();
		List<String> mCatList = new ArrayList<>();
		for (MealCategory rec : allMealCategories) {
			mCatList.add(rec.getName());
		}

		Iterable<ActivityCategory> allActCategories = findInDataBaseService.allActCat();
		List<String> aCatList = new ArrayList<>();
		for (ActivityCategory rec : allActCategories) {
			aCatList.add(rec.getName());
		}

		model.addAttribute("actCat", aCatList);
		model.addAttribute("mealCat", mCatList);

		return "addToDataBase";
	}

	@PostMapping("/addMealToDataBase")
	public String addMeal(Model model, @RequestParam String mealCat, @RequestParam String mealName,
			@RequestParam Integer mealKcal, @RequestParam String mealDesc) {

		Optional<MealCategory> mCat = findInDataBaseService.findMealCatByName(mealCat);

		Meal meal = new Meal();
		meal.setCategoryId(mCat.get().getId());
		meal.setName(mealName);
		meal.setKcalories(mealKcal);
		meal.setDescription(mealDesc);

		addToDataBaseService.addMealToDataBase(meal);
		addToDB(model);
		model.addAttribute("record", meal);

		return "addToDataBase";
	}

	@PostMapping("/addActivityToDataBase")
	public String addToActivity(Model model, @RequestParam String actCat, @RequestParam String actName,
			@RequestParam Integer actKcal, @RequestParam String actDesc) {

		Optional<ActivityCategory> aCat = findInDataBaseService.findActCatByName(actCat);

		Activity a = new Activity();
		a.setCategoryId(aCat.get().getId());
		a.setName(actName);
		a.setKcalories(actKcal);
		a.setDescription(actDesc);

		addToDataBaseService.addActToDataBase(a);
		addToDB(model);
		model.addAttribute("record", a);
		return "addToDataBase";
	}

}
