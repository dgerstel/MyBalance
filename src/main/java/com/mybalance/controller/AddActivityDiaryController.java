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

import com.mybalance.model.Activity;
import com.mybalance.model.ActivityCategory;
import com.mybalance.model.ActivityDiary;
import com.mybalance.model.User;
import com.mybalance.service.AddToDiaryService;
import com.mybalance.service.FindInDataBaseService;
import com.mybalance.service.UserService;

@Controller
@ComponentScan
@RequestMapping
public class AddActivityDiaryController {

	private UserService userService;
	private FindInDataBaseService findInDataBaseService;
	private AddToDiaryService addToDiaryService;

	@Autowired
	public AddActivityDiaryController(UserService userService, FindInDataBaseService findInDataBaseService,
			AddToDiaryService addToDiaryService) {
		this.userService = userService;
		this.findInDataBaseService = findInDataBaseService;
		this.addToDiaryService = addToDiaryService;
	}

	@PostMapping("/diary/addActivityDiary")
	public String allActivities(Model model, @RequestParam String category) {

		Optional<ActivityCategory> ac = findInDataBaseService.findActCatByName(category);
		ActivityCategory activityCategory = ac.get();
		Iterable<Activity> activitiesByCategory = findInDataBaseService.findActByCatId(activityCategory.getId());
		Iterable<ActivityCategory> allCatList = findInDataBaseService.allActCat();

		model.addAttribute("activitiesByCategory", activitiesByCategory);
		model.addAttribute("cat", activityCategory);
		model.addAttribute("allCatList", allCatList);

		return "addActivityDiary";
	}

	@PostMapping("/addAct")
	public String addToActivityDiary(Model model, @RequestParam Integer id, @RequestParam String date, String category,
			Float actSize) {

		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		User currentUser = userService.findUser(currentUserName);

		Optional<Activity> activity = findInDataBaseService.findActById(id);
		Activity a = activity.get();
		ActivityDiary ad = new ActivityDiary();
		ad.setDate(LocalDate.parse(date));
		ad.setActivityId(a.getId());
		ad.setActSize(actSize);
		ad.setUserId(currentUser.getId());

		addToDiaryService.addActToDiary(ad);
		allActivities(model, category);
		model.addAttribute("record", ad);
		model.addAttribute("selectedDate", date);

		return "addActivityDiary";
	}

}
