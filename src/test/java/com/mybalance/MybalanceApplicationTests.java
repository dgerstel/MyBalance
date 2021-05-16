package com.mybalance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mybalance.model.Meal;
import com.mybalance.repository.MealRepository;

@SpringBootTest
class MybalanceApplicationTests {

	@Autowired
	MealRepository mealRepo;
	
	
	@Test
	public void contextLoads() {
	} 
		
	@Test
	public void testCreate() {
	Meal meal = new Meal();
	meal.setCategoryId(1);
	meal.setName("Test meal");
	meal.setDescription("Test description");
	meal.setKcalories(111);
	Meal savedMeal = mealRepo.save(meal);
	assertNotNull(savedMeal);
	}
	
	@Test
	public void testRead() {
	if (mealRepo.existsByName("Test meal")) {
	Meal meal = mealRepo.findByName("Test meal").get();
	assertNotNull(meal);
	assertEquals(111, meal.getKcalories());
	System.out.println(">>>>>>>>>>>>>>>>>" + meal.getName());
	}
	} 
	
	@Test
	public void testUpdate() {
	if (mealRepo.existsByName("Test meal")) {
	Meal meal = mealRepo.findByName("Test meal").get();
	meal.setDescription("Changed description");
	mealRepo.save(meal);
	}
	} 
	
	@Test
	public void testDelete() {
	if (mealRepo.existsByName("Test meal")) {
	System.out.println("Deleting a record");
	mealRepo.deleteByName("TestMeal");
	}
	}
	
	@Test
	public void testCount() {
	System.out.println("Number of records (Meals):" + mealRepo.count());
	}

}
