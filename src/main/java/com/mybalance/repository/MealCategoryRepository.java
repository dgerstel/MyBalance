package com.mybalance.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mybalance.model.MealCategory;

@Repository
public interface MealCategoryRepository extends CrudRepository<MealCategory, Integer> {

	Optional<MealCategory> findByName(String category);
}