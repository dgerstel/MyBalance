package com.mybalance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mybalance.model.FoodDiary;

@Transactional
@Repository
public interface FoodDiaryRepository extends CrudRepository<FoodDiary, Integer> {

	public Iterable<FoodDiary> findByUserId(Integer id);

	public void deleteByMealId(Integer id);

	public void deleteByUserId(Integer id);
}