package com.mybalance.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mybalance.model.FoodDiary;
import com.mybalance.model.Meal;
import com.mybalance.model.MealCategory;
import com.mybalance.model.User;
import com.mybalance.service.AddToDiaryService;
import com.mybalance.service.FindInDataBaseService;
import com.mybalance.service.UserService;

@Controller
@ComponentScan
@RequestMapping
public class AddFoodDiaryController {

	private UserService userService;
	private FindInDataBaseService findInDataBaseService;
	private AddToDiaryService addToDiaryService;

	@Autowired
	public AddFoodDiaryController(UserService userService, FindInDataBaseService findInDataBaseService,
			AddToDiaryService addToDiaryService) {
		this.userService = userService;
		this.findInDataBaseService = findInDataBaseService;
		this.addToDiaryService = addToDiaryService;
	}

	@PostMapping("/diary/addFoodDiary")
	public String allMeals(Model model, @RequestParam String category) {

		Optional<MealCategory> mc = findInDataBaseService.findMealCatByName(category);
		MealCategory mealCategory = mc.get();
		Iterable<Meal> mealsByCategory = findInDataBaseService.findMealByCatId(mealCategory.getId());
		Iterable<MealCategory> allCatList = findInDataBaseService.allMealCat();

		model.addAttribute("mealsByCategory", mealsByCategory);
		model.addAttribute("cat", mealCategory);
		model.addAttribute("allCatList", allCatList);

		return "addFoodDiary";
	}

	@PostMapping("/addFood")
	public String addToFoodDiary(Model model, @RequestParam Integer id, @RequestParam String date, String category,
			Float mealSize) {

		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		User currentUser = userService.findUser(currentUserName);

		Optional<Meal> meal = findInDataBaseService.findMealById(id);
		Meal m = meal.get();
		FoodDiary fd = new FoodDiary();
		fd.setDate(LocalDate.parse(date));
		fd.setMealId(m.getId());
		fd.setMealSize(mealSize);
		fd.setUserId(currentUser.getId());

		addToDiaryService.addFoodToDiary(fd);
		allMeals(model, category);
		model.addAttribute("record", fd);
		model.addAttribute("selectedDate", date);

		return "addFoodDiary";
	}

}
