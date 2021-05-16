package com.mybalance.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mybalance.model.Meal;

@Repository
public interface MealRepository extends CrudRepository<Meal, Integer> {

	Optional<Meal> findByName(String name);

	Iterable<Meal> findByCategoryId(Integer id);

	boolean existsByName(String string);

	void deleteByName(String string);

}