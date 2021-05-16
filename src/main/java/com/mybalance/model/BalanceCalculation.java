package com.mybalance.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class BalanceCalculation {
	private LocalDate date;
	private Integer kcalIn;
	private Integer kcalSpent;
	private Integer basicExp;
	private Integer kcalBalance;
	private Integer balanceRate;
	private VerbalBalanceRating balanceDesc;

	public BalanceCalculation() {
		// TODO Auto-generated constructor stub
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public DayOfWeek dayOfWeek() {
		DayOfWeek day = date.getDayOfWeek();
		return day;
	}

	public Integer getKcalIn() {
		return kcalIn;
	}

	public void setKcalIn(Integer kcalIn) {
		this.kcalIn = kcalIn;
	}

	public Integer getKcalSpent() {
		return kcalSpent;
	}

	public void setKcalSpent(Integer kcalSpent) {
		this.kcalSpent = kcalSpent;
	}

	public Integer getBasicExp() {
		return basicExp;
	}

	public void setBasicExp(Integer basicExp) {
		this.basicExp = basicExp;
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

	public Integer getKcalBalance() {
		return kcalBalance;
	}

	public void setKcalBalance(Integer kcalBalance) {
		this.kcalBalance = kcalBalance;
	}

}
