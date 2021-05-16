package com.mybalance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MealCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	String name;
	String description;

	public MealCategory() {
	}

	public MealCategory(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
