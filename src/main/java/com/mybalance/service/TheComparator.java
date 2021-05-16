package com.mybalance.service;

import java.util.Comparator;

import com.mybalance.model.Date;

public class TheComparator implements Comparator<Date> {

	@Override
	public int compare(Date o1, Date o2) {
		return o2.getDate().compareTo(o1.getDate());
	}
}