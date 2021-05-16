package com.mybalance.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mybalance.model.ActivityCategory;
import com.mybalance.model.Date;
import com.mybalance.model.Display;
import com.mybalance.model.MealCategory;
import com.mybalance.model.User;
import com.mybalance.service.DatesService;
import com.mybalance.service.DeleteDiaryService;
import com.mybalance.service.DisplayDiaryService;
import com.mybalance.service.FindInDataBaseService;
import com.mybalance.service.UserService;

@Controller
@ComponentScan
@RequestMapping
public class DiaryController {

	private UserService userService;
	private DisplayDiaryService displayDiaryService;
	private DatesService datesService;
	private FindInDataBaseService findInDataBaseService;
	private DeleteDiaryService delDiaryService;

	@Autowired
	public DiaryController(UserService userService, DisplayDiaryService displayDiaryService, DatesService datesService,
			FindInDataBaseService findInDataBaseService, DeleteDiaryService delDiaryService) {
		this.userService = userService;
		this.displayDiaryService = displayDiaryService;
		this.datesService = datesService;
		this.findInDataBaseService = findInDataBaseService;
		this.delDiaryService = delDiaryService;
	}

	@RequestMapping("/diary")
	public String showAll(Model model) {

		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		User currentUser = userService.findUser(currentUserName);
		String userDispl = currentUserName;

		Iterable<MealCategory> allMealCategories = findInDataBaseService.allMealCat();
		List<String> mealCategoriesList = new ArrayList<>();
		for (MealCategory record : allMealCategories) {
			mealCategoriesList.add(record.getName());
		}
		Iterable<ActivityCategory> allActCategories = findInDataBaseService.allActCat();
		List<String> actCategoriesList = new ArrayList<>();
		for (ActivityCategory record : allActCategories) {
			actCategoriesList.add(record.getName());
		}
		Set<Date> dates = datesService.datesForUserLimit(currentUser, 14);
		List<Display> allDisplay = displayDiaryService.displayAllRecords(currentUserName);

		model.addAttribute("userDispl", userDispl);
		model.addAttribute("allDisplay", allDisplay);
		model.addAttribute("mealCategoriesList", mealCategoriesList);
		model.addAttribute("actCategoriesList", actCategoriesList);
		model.addAttribute("dates", dates);
		return "diary";
	}

	@PostMapping("/foodDiaryToDelete")
	public String foodDiaryToDelete(Model model, @RequestParam Integer id) {

		delDiaryService.deleteFoodDiary(id);
		showAll(model);
		return "diary";
	}

	@PostMapping("/activityDiaryToDelete")
	public String activityDiaryToDelete(Model model, @RequestParam Integer id) {

		delDiaryService.deleteActivityDiary(id);
		showAll(model);
		return "diary";
	}

	@PostMapping("/selectDates")
	public String selectedDatesDiary(Model model, @RequestParam String fromDate, String toDate) {

		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		User currentUser = userService.findUser(currentUserName);
		Set<Date> dates = datesService.selectDatesForUser(currentUser, LocalDate.parse(fromDate),
				LocalDate.parse(toDate));

		showAll(model);
		model.addAttribute("dates", dates);
		return "diary";
	}

}