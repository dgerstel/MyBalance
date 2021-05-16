package com.mybalance.service;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.mybalance.model.ActivityDiary;
import com.mybalance.model.Date;
import com.mybalance.model.FoodDiary;
import com.mybalance.model.User;
import com.mybalance.repository.ActivityDiaryRepository;
import com.mybalance.repository.FoodDiaryRepository;
import com.mybalance.repository.UserRepository;

@Service
@ComponentScan
public class DatesService {

	private FoodDiaryRepository foodDiaryRepository;
	private UserRepository userRepository;
	private ActivityDiaryRepository activityDiaryRepository;

	@Autowired
	public DatesService(FoodDiaryRepository foodDiaryRepository, ActivityDiaryRepository activityDiaryRepository,
			UserRepository userRepository) {
		this.foodDiaryRepository = foodDiaryRepository;
		this.userRepository = userRepository;
		this.activityDiaryRepository = activityDiaryRepository;
	}

	public TreeSet<Date> allDatesForUser(User user) {

		Iterable<FoodDiary> allMealsForUser = foodDiaryRepository.findByUserId(user.getId());
		Iterable<ActivityDiary> allActivitiesForUser = activityDiaryRepository.findByUserId(user.getId());
		Set<Date> dates = new TreeSet<>(new TheComparator());

		for (FoodDiary record : allMealsForUser) {
			Date d = new Date();
			d.setDate(record.getDate());
			dates.add(d);
		}
		for (ActivityDiary record : allActivitiesForUser) {
			Date d = new Date();
			d.setDate(record.getDate());
			dates.add(d);
		}
		return (TreeSet<Date>) dates;
	}

	public TreeSet<Date> datesForUserLimit(User user, Integer limit) {

		DatesService dService = new DatesService(foodDiaryRepository, activityDiaryRepository, userRepository);
		Set<Date> allDates = dService.allDatesForUser(user);

		Set<Date> limitDates = new TreeSet<>(new TheComparator());

		for (Date date : allDates) {
			if (limitDates.size() < limit)
				limitDates.add(date);
		}
		return (TreeSet<Date>) limitDates;
	}

	public TreeSet<Date> selectDatesForUser(User user, LocalDate fromDate, LocalDate toDate) {

		DatesService dService = new DatesService(foodDiaryRepository, activityDiaryRepository, userRepository);
		Set<Date> allDates = dService.allDatesForUser(user);

		Set<Date> selectedDates = new TreeSet<>(new TheComparator());
		LocalDate minDate = fromDate;
		LocalDate maxDate = toDate;

		if (minDate.isAfter(maxDate)) {
			LocalDate sDate = minDate;
			minDate = maxDate;
			maxDate = sDate;
		}
		for (Date date : allDates) {
			LocalDate dDate = date.getDate();
			if (dDate.isAfter(minDate) | dDate.equals(minDate) && dDate.isBefore(maxDate) | dDate.equals(maxDate)) {
				selectedDates.add(date);
			}
		}
		return (TreeSet<Date>) selectedDates;
	}
}
