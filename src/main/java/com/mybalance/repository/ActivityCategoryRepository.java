package com.mybalance.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mybalance.model.ActivityCategory;

@Repository
public interface ActivityCategoryRepository extends CrudRepository<ActivityCategory, Integer> {

	Optional<ActivityCategory> findByName(String category);
}