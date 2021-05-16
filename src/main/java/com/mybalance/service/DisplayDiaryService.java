package com.mybalance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.mybalance.model.Activity;
import com.mybalance.model.ActivityCategory;
import com.mybalance.model.ActivityDiary;
import com.mybalance.model.Display;
import com.mybalance.model.FoodDiary;
import com.mybalance.model.Meal;
import com.mybalance.model.MealCategory;
import com.mybalance.model.User;
import com.mybalance.repository.ActivityCategoryRepository;
import com.mybalance.repository.ActivityDiaryRepository;
import com.mybalance.repository.ActivityRepository;
import com.mybalance.repository.FoodDiaryRepository;
import com.mybalance.repository.MealCategoryRepository;
import com.mybalance.repository.MealRepository;
import com.mybalance.repository.UserRepository;

@Service
@ComponentScan
public class DisplayDiaryService {

	private UserRepository userRepository;
	private FoodDiaryRepository foodDiaryRepository;
	private ActivityDiaryRepository activityDiaryRepository;
	private MealCategoryRepository mealCategoryRepository;
	private ActivityCategoryRepository activityCategoryRepository;
	private MealRepository mealRepository;
	private ActivityRepository activityRepository;

	@Autowired
	public DisplayDiaryService(FoodDiaryRepository foodDiaryRepository, UserRepository userRepository,
			ActivityDiaryRepository activityDiaryRepository, MealCategoryRepository mealCategoryRepository,
			MealRepository mealRepository, ActivityCategoryRepository activityCategoryRepository,
			ActivityRepository activityRepository) {
		this.foodDiaryRepository = foodDiaryRepository;
		this.userRepository = userRepository;
		this.activityDiaryRepository = activityDiaryRepository;
		this.mealCategoryRepository = mealCategoryRepository;
		this.mealRepository = mealRepository;
		this.activityCategoryRepository = activityCategoryRepository;
		this.activityRepository = activityRepository;
	}

	public List<Display> displayAllRecords(String userName) {

		List<Display> displayAll = new ArrayList<>();

		User user = userRepository.findByUserName(userName);

		Iterable<FoodDiary> allMealsForUser = foodDiaryRepository.findByUserId(user.getId());
		Iterable<ActivityDiary> allActivitiesForUser = activityDiaryRepository.findByUserId(user.getId());

		for (FoodDiary record : allMealsForUser) {
			Display display = new Display();
			display.setDate(record.getDate());
			Optional<Meal> meal = mealRepository.findById(record.getMealId());
			display.setNameMeal(meal.get().getName());
			display.setMealSize(record.getMealSize());
			display.setKcaloriesMeal(setKcaloriesMethod(record));
			Optional<MealCategory> mealCategory = mealCategoryRepository.findById(meal.get().getCategoryId());
			display.setCategoryMeal(mealCategory.get().getName());
			display.setFdId(record.getId());
			displayAll.add(display);
		}

		for (ActivityDiary record : allActivitiesForUser) {
			Display display = new Display();
			display.setDate(record.getDate());
			Optional<Activity> activity = activityRepository.findById(record.getActivityId());
			display.setNameAct(activity.get().getName());
			display.setActSize(record.getActSize());
			display.setKcaloriesAct(setKcaloriesMethod(record));
			Optional<ActivityCategory> activityCategory = activityCategoryRepository
					.findById(activity.get().getCategoryId());
			display.setCategoryAct(activityCategory.get().getName());
			display.setAdId(record.getId());
			displayAll.add(display);
		}
		return displayAll;
	}

	public Integer setKcaloriesMethod(FoodDiary fd) {
		Optional<Meal> meal = mealRepository.findById(fd.getMealId());
		Integer kcal = meal.get().getKcalories();
		if (fd.getMealSize() != null)
			return Math.toIntExact(Math.round(kcal * fd.getMealSize()));
		return kcal;
	}

	public Integer setKcaloriesMethod(ActivityDiary ad) {
		Optional<Activity> act = activityRepository.findById(ad.getActivityId());
		Integer kcal = act.get().getKcalories();
		if (ad.getActSize() != null)
			return Math.toIntExact(Math.round(kcal * ad.getActSize()));
		return kcal;
	}

	public Iterable<FoodDiary> displayFoodDiary() {
		Iterable<FoodDiary> allFoodDiary = foodDiaryRepository.findAll();
		return allFoodDiary;
	}

	public Iterable<ActivityDiary> displayActDiary() {
		Iterable<ActivityDiary> allFoodDiary = activityDiaryRepository.findAll();
		return allFoodDiary;
	}

	public long countFoodDiary() {
		return foodDiaryRepository.count();
	}

	public long countActDiary() {
		return activityDiaryRepository.count();
	}

	public long countMeal() {
		return mealRepository.count();
	}

	public long countAct() {
		return activityRepository.count();
	}

	public long countUsers() {
		return userRepository.count();
	}

	public String getStatistics() {
		String statistics = "FoodDiary: " + Long.toString(countFoodDiary()) + "; ActDiary: "
				+ Long.toString(countActDiary()) + "; Meals: " + Long.toString(countMeal()) + "; Acts: "
				+ Long.toString(countAct()) + "; Users: " + Long.toString(countUsers());
		return statistics;
	}
}
