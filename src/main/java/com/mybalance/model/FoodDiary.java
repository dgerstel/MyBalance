package com.mybalance.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FoodDiary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private LocalDate date;
	private Integer mealId;
	private Integer userId;
	private Float mealSize;

	public FoodDiary() {
	}

	public FoodDiary(LocalDate date, Integer mealId, Integer userId) {
		this.date = date;
		this.mealId = mealId;
		this.userId = userId;
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

	public Integer getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Float getMealSize() {
		return mealSize;
	}

	public void setMealSize(Float mealSize) {
		this.mealSize = mealSize;
	}

	@Override
	public String toString() {
		return "FoodDiary [id=" + id + ", date=" + date + ", mealId=" + mealId + ", userId=" + userId + ", mealSize="
				+ mealSize + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mealId == null) ? 0 : mealId.hashCode());
		result = prime * result + ((mealSize == null) ? 0 : mealSize.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodDiary other = (FoodDiary) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mealId == null) {
			if (other.mealId != null)
				return false;
		} else if (!mealId.equals(other.mealId))
			return false;
		if (mealSize == null) {
			if (other.mealSize != null)
				return false;
		} else if (!mealSize.equals(other.mealSize))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}