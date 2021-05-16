package com.mybalance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.mybalance.model.Activity;
import com.mybalance.model.ActivityDiary;
import com.mybalance.model.BalanceCalculation;
import com.mybalance.model.Date;
import com.mybalance.model.FoodDiary;
import com.mybalance.model.Meal;
import com.mybalance.model.TotalBalanceCalculation;
import com.mybalance.model.User;
import com.mybalance.model.VerbalBalanceRating;
import com.mybalance.repository.ActivityDiaryRepository;
import com.mybalance.repository.ActivityRepository;
import com.mybalance.repository.FoodDiaryRepository;
import com.mybalance.repository.MealRepository;
import com.mybalance.repository.UserRepository;

@Service
@ComponentScan
public class BalanceCalculationService {

	private UserRepository userRepository;
	private FoodDiaryRepository foodDiaryRepository;
	private ActivityDiaryRepository activityDiaryRepository;
	private MealRepository mealRepository;
	private ActivityRepository activityRepository;
	private DatesService datesService;

	@Autowired
	public BalanceCalculationService(UserRepository userRepository, FoodDiaryRepository foodDiaryRepository,
			ActivityDiaryRepository activityDiaryRepository, MealRepository mealRepository,
			ActivityRepository activityRepository, DatesService datesService) {
		this.foodDiaryRepository = foodDiaryRepository;
		this.userRepository = userRepository;
		this.activityDiaryRepository = activityDiaryRepository;
		this.mealRepository = mealRepository;
		this.activityRepository = activityRepository;
		this.datesService = datesService;
	}

	public List<BalanceCalculation> calculateBalance(User user) {

		List<BalanceCalculation> calculations = new ArrayList<>();
		Set<Date> dates = datesService.datesForUserLimit(user, 14);

		if (dates == null)
			return null;

		Iterable<FoodDiary> allMealsForUser = foodDiaryRepository.findByUserId(user.getId());
		Iterable<ActivityDiary> allActivitiesForUser = activityDiaryRepository.findByUserId(user.getId());

		for (Date date : dates) {
			BalanceCalculation bc = new BalanceCalculation();
			bc.setDate(date.getDate());
			int basicExp = user.getBasicExpenseWithJob();
			bc.setBasicExp(basicExp);
			int kcalInSum = 0;
			int kcalSpentSum = 0;

			for (FoodDiary rec : allMealsForUser) {
				if (rec.getDate().equals(date.getDate())) {
					kcalInSum += setKcaloriesMethod(rec);
				}
			}
			bc.setKcalIn(kcalInSum);

			for (ActivityDiary recA : allActivitiesForUser) {
				if (recA.getDate().equals(date.getDate())) {
					kcalSpentSum += setKcaloriesMethod(recA);
				}
			}
			bc.setKcalSpent(kcalSpentSum);
			bc.setKcalBalance(kcalBalanceMethod(kcalInSum, kcalSpentSum, basicExp));
			int balRate = balanceRateMethod(kcalInSum, kcalSpentSum, basicExp);
			bc.setBalanceRate(balRate);
			bc.setBalanceDesc(balanceDescMethod(balRate));

			calculations.add(bc);
		}
		return calculations;
	}

	public TotalBalanceCalculation calculateTotalBalance(User user) {

		TotalBalanceCalculation calculationAll = new TotalBalanceCalculation();

		BalanceCalculationService bCalcService = new BalanceCalculationService(userRepository, foodDiaryRepository,
				activityDiaryRepository, mealRepository, activityRepository, datesService);

		List<BalanceCalculation> calculation = bCalcService.calculateBalance(user);

		if (calculation == null)
			return null;

		int tBasExp = 0;
		int tKcalIn = 0;
		int tKcalSpent = 0;
		int recordsNumber = calculation.size();

		for (BalanceCalculation calc : calculation) {
			tBasExp += calc.getBasicExp();
			tKcalIn += calc.getKcalIn();
			tKcalSpent += calc.getKcalSpent();
		}
		calculationAll.setTotalBasicExp(tBasExp);
		calculationAll.setTotalKcalIn(tKcalIn);
		calculationAll.setTotalKcalSpent(tKcalSpent);
		int kaclBal = kcalBalanceMethod(tKcalIn, tKcalSpent, tBasExp);
		calculationAll.setKcalBalance(kaclBal);
		int balRate = balanceRateMethod(tKcalIn, tKcalSpent, tBasExp);
		calculationAll.setBalanceRate(balRate);
		calculationAll.setBalanceDesc(balanceDescMethod(balRate));
		calculationAll.setAverageBalance(averageBalanceMethod(kaclBal, recordsNumber));

		return calculationAll;
	}

	public List<BalanceCalculation> calculateBalanceInScope(User user, LocalDate fromDate, LocalDate toDate) {
		List<BalanceCalculation> calculations = new ArrayList<>();
		Set<Date> dates = datesService.selectDatesForUser(user, fromDate, toDate);
		Iterable<FoodDiary> allMealsForUser = foodDiaryRepository.findByUserId(user.getId());
		Iterable<ActivityDiary> allActivitiesForUser = activityDiaryRepository.findByUserId(user.getId());

		for (Date date : dates) {
			BalanceCalculation bc = new BalanceCalculation();
			bc.setDate(date.getDate());
			int basicExp = user.getBasicExpenseWithJob();
			bc.setBasicExp(basicExp);
			int kcalInSum = 0;
			int kcalSpentSum = 0;
			for (FoodDiary rec : allMealsForUser) {
				if (rec.getDate().equals(date.getDate())) {
					kcalInSum += setKcaloriesMethod(rec);
				}
			}
			bc.setKcalIn(kcalInSum);

			for (ActivityDiary recA : allActivitiesForUser) {
				if (recA.getDate().equals(date.getDate())) {
					kcalSpentSum += setKcaloriesMethod(recA);
				}
			}
			bc.setKcalSpent(kcalSpentSum);
			bc.setKcalBalance(kcalBalanceMethod(kcalInSum, kcalSpentSum, basicExp));
			int balRate = balanceRateMethod(kcalInSum, kcalSpentSum, basicExp);
			bc.setBalanceRate(balRate);
			bc.setBalanceDesc(balanceDescMethod(balRate));
			calculations.add(bc);
		}
		return calculations;
	}

	public TotalBalanceCalculation calculateTotalBalanceInScope(User user, LocalDate fromDate, LocalDate toDate) {

		TotalBalanceCalculation calculationAll = new TotalBalanceCalculation();

		BalanceCalculationService bCalcService = new BalanceCalculationService(userRepository, foodDiaryRepository,
				activityDiaryRepository, mealRepository, activityRepository, datesService);

		List<BalanceCalculation> calculation = bCalcService.calculateBalanceInScope(user, fromDate, toDate);

		if (calculation == null)
			return null;

		int tBasExp = 0;
		int tKcalIn = 0;
		int tKcalSpent = 0;
		int recordsNumber = calculation.size();

		for (BalanceCalculation calc : calculation) {
			tBasExp += calc.getBasicExp();
			tKcalIn += calc.getKcalIn();
			tKcalSpent += calc.getKcalSpent();
		}
		calculationAll.setTotalBasicExp(tBasExp);
		calculationAll.setTotalKcalIn(tKcalIn);
		calculationAll.setTotalKcalSpent(tKcalSpent);
		calculationAll.setFromDate(fromDate);
		calculationAll.setToDate(toDate);
		int kaclBal = kcalBalanceMethod(tKcalIn, tKcalSpent, tBasExp);
		calculationAll.setKcalBalance(kaclBal);
		int balRate = balanceRateMethod(tKcalIn, tKcalSpent, tBasExp);
		calculationAll.setBalanceRate(balRate);
		calculationAll.setBalanceDesc(balanceDescMethod(balRate));
		calculationAll.setAverageBalance(averageBalanceMethod(kaclBal, recordsNumber));

		return calculationAll;
	}

	public Integer setKcaloriesMethod(FoodDiary fd) {
		Optional<Meal> meal = mealRepository.findById(fd.getMealId());
		Integer kcal = meal.get().getKcalories();
		if (fd.getMealSize() != null)
			return Math.toIntExact(Math.round(kcal * fd.getMealSize()));
		return kcal;
	}

	public Integer setKcaloriesMethod(ActivityDiary ad) {
		Optional<Activity> act = activityRepository.findById(ad.getActivityId());
		Integer kcal = act.get().getKcalories();
		if (ad.getActSize() != null)
			return Math.toIntExact(Math.round(kcal * ad.getActSize()));
		return kcal;
	}

	public Integer kcalBalanceMethod(Integer kcalIn, Integer kcalSpent, Integer basicExp) {
		Integer kcalBalance = kcalIn - kcalSpent - basicExp;
		return kcalBalance;
	}

	public Integer balanceRateMethod(Integer kcalIn, Integer kcalSpent, Integer basicExp) {
		double in = kcalIn;
		double exp = basicExp + kcalSpent;
		double result;
		double balanceRate;
		if (exp > 0) {
			result = (double) (in / exp) * 100;
			balanceRate = Math.round(result);
			return (int) balanceRate;
		}
		return -1;
	}

	public VerbalBalanceRating balanceDescMethod(Integer balanceRate) {
		if (balanceRate <= 105 && balanceRate >= 95)
			return VerbalBalanceRating.WYROWNANY;
		if (balanceRate > 105)
			return VerbalBalanceRating.NADWYZKA;
		return VerbalBalanceRating.DEFICYT;
	}

	public Integer averageBalanceMethod(Integer kcalBalance, Integer numberOfRecords) {
		if (numberOfRecords > 0)
			return kcalBalance / numberOfRecords;
		return 0;
	}
}
