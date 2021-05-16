package com.mybalance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybalance.repository.ActivityDiaryRepository;
import com.mybalance.repository.ActivityRepository;
import com.mybalance.repository.FoodDiaryRepository;
import com.mybalance.repository.MealRepository;

@Service
public class DeleteDiaryService {

	private FoodDiaryRepository foodDiaryRepo;
	private ActivityDiaryRepository actDiaryRepo;
	private ActivityRepository actRepo;
	private MealRepository mealRepo;

	@Autowired
	public DeleteDiaryService(FoodDiaryRepository foodDiaryRepo, ActivityDiaryRepository actDiaryRepo,
			ActivityRepository actRepo, MealRepository mealRepo) {
		this.foodDiaryRepo = foodDiaryRepo;
		this.actDiaryRepo = actDiaryRepo;
		this.actRepo = actRepo;
		this.mealRepo = mealRepo;
	}

	public void deleteFoodDiary(Integer id) {
		foodDiaryRepo.deleteById(id);
	}

	public void deleteActivityDiary(Integer id) {
		actDiaryRepo.deleteById(id);
	}

	public void deleteActivity(Integer id) {
		actDiaryRepo.deleteByActivityId(id);
		actRepo.deleteById(id);
	}

	public void deleteMeal(Integer id) {
		foodDiaryRepo.deleteByMealId(id);
		mealRepo.deleteById(id);
	}
}
