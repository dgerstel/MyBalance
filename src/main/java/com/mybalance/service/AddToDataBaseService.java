package com.mybalance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybalance.model.Activity;
import com.mybalance.model.Meal;
import com.mybalance.repository.ActivityRepository;
import com.mybalance.repository.MealRepository;

@Service
public class AddToDataBaseService {

	private MealRepository mealRepo;
	private ActivityRepository actRepo;

	@Autowired
	public AddToDataBaseService(MealRepository mealRepo, ActivityRepository actRepo) {
		super();
		this.mealRepo = mealRepo;
		this.actRepo = actRepo;
	}

	public void addMealToDataBase(Meal m) {
		mealRepo.save(m);
	}

	public void addActToDataBase(Activity a) {
		actRepo.save(a);
	}
}
