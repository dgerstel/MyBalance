package com.mybalance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybalance.model.ActivityDiary;
import com.mybalance.model.FoodDiary;
import com.mybalance.repository.ActivityDiaryRepository;
import com.mybalance.repository.FoodDiaryRepository;

@Service
public class AddToDiaryService {

	ActivityDiaryRepository actDiaryRepo;
	FoodDiaryRepository foodDiaryRepo;

	@Autowired
	public AddToDiaryService(ActivityDiaryRepository actDiaryRepo, FoodDiaryRepository foodDiaryRepo) {
		this.actDiaryRepo = actDiaryRepo;
		this.foodDiaryRepo = foodDiaryRepo;
	}

	public void addActToDiary(ActivityDiary ad) {
		actDiaryRepo.save(ad);
	}

	public void addFoodToDiary(FoodDiary fd) {
		foodDiaryRepo.save(fd);
	}

}
