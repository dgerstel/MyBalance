package com.mybalance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mybalance.model.ActivityDiary;

@Transactional
@Repository
public interface ActivityDiaryRepository extends CrudRepository<ActivityDiary, Integer> {

	public Iterable<ActivityDiary> findByUserId(Integer id);

	public void deleteByActivityId(Integer id);

	public void deleteByUserId(Integer id);
}
