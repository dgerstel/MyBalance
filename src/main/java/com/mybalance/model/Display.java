package com.mybalance.model;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

public class Display {

	private LocalDate date;
	private String nameMeal;
	private String categoryMeal;
	private Integer kcaloriesMeal;
	private Float mealSize;
	private Float actSize;
	private Integer fdId;
	private String nameAct;
	private String categoryAct;
	private Integer kcaloriesAct;
	private Integer adId;

	public Float getMealSize() {
		return mealSize;
	}

	public void setMealSize(Float mealSize) {
		this.mealSize = mealSize;
	}

	public Float getActSize() {
		return actSize;
	}

	public void setActSize(Float actSize) {
		this.actSize = actSize;
	}

	@Autowired
	public Display() {
		// TODO Auto-generated constructor stub
	}

	public Integer getFdId() {
		return fdId;
	}

	public void setFdId(Integer fdId) {
		this.fdId = fdId;
	}

	public Integer getAdId() {
		return adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getNameMeal() {
		return nameMeal;
	}

	public void setNameMeal(String nameMeal) {
		this.nameMeal = nameMeal;
	}

	public String getCategoryMeal() {
		return categoryMeal;
	}

	public void setCategoryMeal(String categoryMeal) {
		this.categoryMeal = categoryMeal;
	}

	public Integer getKcaloriesMeal() {
		return kcaloriesMeal;
	}

	public void setKcaloriesMeal(Integer kcaloriesMeal) {
		this.kcaloriesMeal = kcaloriesMeal;
	}

	public String getNameAct() {
		return nameAct;
	}

	public void setNameAct(String nameAct) {
		this.nameAct = nameAct;
	}

	public String getCategoryAct() {
		return categoryAct;
	}

	public void setCategoryAct(String categoryAct) {
		this.categoryAct = categoryAct;
	}

	public Integer getKcaloriesAct() {
		return kcaloriesAct;
	}

	public void setKcaloriesAct(Integer kcaloriesAct) {
		this.kcaloriesAct = kcaloriesAct;
	}

}
