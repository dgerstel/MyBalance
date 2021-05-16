package com.mybalance.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class TotalBalanceCalculation {
	private LocalDate fromDate;
	private LocalDate toDate;
	private Integer totalKcalIn;
	private Integer totalKcalSpent;
	private Integer totalBasicExp;
	private Integer kcalBalance;
	private Integer balanceRate;
	private VerbalBalanceRating balanceDesc;
	private Integer averageBalance;

	public TotalBalanceCalculation() {
		// TODO Auto-generated constructor stub
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public Integer getTotalKcalIn() {
		return totalKcalIn;
	}

	public void setTotalKcalIn(Integer totalKcalIn) {
		this.totalKcalIn = totalKcalIn;
	}

	public Integer getTotalKcalSpent() {
		return totalKcalSpent;
	}

	public void setTotalKcalSpent(Integer totalKcalSpent) {
		this.totalKcalSpent = totalKcalSpent;
	}

	public Integer getTotalBasicExp() {
		return totalBasicExp;
	}

	public void setTotalBasicExp(Integer totalBasicExp) {
		this.totalBasicExp = totalBasicExp;
	}

	public DayOfWeek dayOfWeekFrom() {
		DayOfWeek day = fromDate.getDayOfWeek();
		return day;
	}

	public DayOfWeek dayOfWeekTo() {
		DayOfWeek day = toDate.getDayOfWeek();
		return day;
	}

	public Integer getKcalBalance() {
		return kcalBalance;
	}

	public void setKcalBalance(Integer kcalBalance) {
		this.kcalBalance = kcalBalance;
	}

	public Integer getBalanceRate() {
		return balanceRate;
	}

	public void setBalanceRate(Integer balanceRate) {
		this.balanceRate = balanceRate;
	}

	public VerbalBalanceRating getBalanceDesc() {
		return balanceDesc;
	}

	public void setBalanceDesc(VerbalBalanceRating balanceDesc) {
		this.balanceDesc = balanceDesc;
	}

	public Integer getAverageBalance() {
		return averageBalance;
	}

	public void setAverageBalance(Integer averageBalance) {
		this.averageBalance = averageBalance;
	}
}
