package com.mybalance.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ActivityDiary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private LocalDate date;
	private Integer activityId;
	private Integer userId;
	private Float actSize;

	public ActivityDiary() {
	}

	public Float getActSize() {
		return actSize;
	}

	public void setActSize(Float actSize) {
		this.actSize = actSize;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ActivityDiary [id=" + id + ", date=" + date + ", activityId=" + activityId + ", userId=" + userId
				+ ", actSize=" + actSize + "]";
	}

}