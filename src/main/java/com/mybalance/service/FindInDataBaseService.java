package com.mybalance.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybalance.model.Activity;
import com.mybalance.model.ActivityCategory;
import com.mybalance.model.Meal;
import com.mybalance.model.MealCategory;
import com.mybalance.repository.ActivityCategoryRepository;
import com.mybalance.repository.ActivityRepository;
import com.mybalance.repository.MealCategoryRepository;
import com.mybalance.repository.MealRepository;

@Service
public class FindInDataBaseService {

	private ActivityCategoryRepository actCatRepo;
	private ActivityRepository actRepo;

	private MealCategoryRepository mealCatRepo;
	private MealRepository mealRepo;

	@Autowired
	public FindInDataBaseService(ActivityRepository actRepo, ActivityCategoryRepository actCatRepo,
			MealCategoryRepository mealCatRepo, MealRepository mealRepo) {
		this.actRepo = actRepo;
		this.actCatRepo = actCatRepo;
		this.mealCatRepo = mealCatRepo;
		this.mealRepo = mealRepo;
	}

	public Optional<MealCategory> findMealCatByName(String name) {
		return mealCatRepo.findByName(name);
	}

	public Iterable<MealCategory> allMealCat() {
		return mealCatRepo.findAll();
	}

	public Iterable<Meal> findMealByCatId(Integer id) {
		return mealRepo.findByCategoryId(id);
	}

	public Optional<Meal> findMealById(Integer id) {
		return mealRepo.findById(id);
	}

	public Optional<ActivityCategory> findActCatByName(String name) {
		return actCatRepo.findByName(name);
	}

	public Iterable<ActivityCategory> allActCat() {
		return actCatRepo.findAll();
	}

	public Iterable<Activity> findActByCatId(Integer id) {
		return actRepo.findByCategoryId(id);
	}

	public Optional<Activity> findActById(Integer id) {
		return actRepo.findById(id);
	}

	public Iterable<Meal> allMeals() {
		return mealRepo.findAll();
	}

	public Iterable<Activity> allActs() {
		return actRepo.findAll();
	}
}
