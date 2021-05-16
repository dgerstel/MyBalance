package com.mybalance.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mybalance.model.Activity;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer> {

	Optional<Activity> findByName(String name);

	Iterable<Activity> findByCategoryId(Integer id);
}