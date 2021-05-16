package com.mybalance.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "To pole nie może być puste. Podaj nazwę użytkownika.")
	@Size(min = 2, max = 30)
	private String userName;
	@NotEmpty(message = "To pole nie może być puste. Podaj email.")
	@Email(message = "Podaj prawidłowy adres email.")
	private String email;
	@NotEmpty(message = "To pole nie może być puste. Musisz utworzyć hasło.")
	@Size(min = 2)
	private String password;
	@NotNull(message = "Należy wybrać jedną z opcji")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Min(18)
	@Max(99)
	@NotNull(message = "Pole 'Wiek' nie może być puste. Wartość musi zawierać się pomiędzy 18-99")
	private Integer age;
	@Min(120)
	@Max(220)
	@NotNull(message = "Pole 'Wzrost' musi mieć prawidłową wartość; nie może też zostać puste.")
	private Integer height;
	@Min(35)
	@Max(300)
	@NotNull(message = "To pole nie może być puste.")
	private Integer weight;
	@NotNull(message = "Należy wybrać jedną z możliwości wskznika aktywności.")
	private Double jobActivityLevel;
	private Integer role;

	public Double getJobActivityLevel() {
		return jobActivityLevel;
	}

	public void setJobActivityLevel(Double jobActivityLevel) {
		this.jobActivityLevel = jobActivityLevel;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getBasicExpense() {

		if (gender.equals(Gender.MALE)) {
			return Math.toIntExact(Math.round(((10 * weight) + (6.25 * height) - (5 * age) + 5)));
		}
		return Math.toIntExact(Math.round(((10 * weight) + (6.25 * height) - (5 * age) - 161)));
	}

	public Integer getBasicExpenseWithJob() {
		if (gender.equals(Gender.MALE)) {
			return Math.toIntExact(Math.round(((10 * weight) + (6.25 * height) - (5 * age) + 5) * jobActivityLevel));
		}
		return Math.toIntExact(Math.round(((10 * weight) + (6.25 * height) - (5 * age) - 161) * jobActivityLevel));
	}

	public Double getBmi() {
		double dHeight = height;
		double result = weight / ((dHeight / 100) * (dHeight / 100));
		return Math.round(result * 10.0) / 10.0;
	}

	public BmiRate rateBmi() {
		double bmi = getBmi();
		if (bmi < 16)
			return BmiRate.WYGŁODZENIE;
		if (bmi >= 16 && bmi < 17)
			return BmiRate.WYCHUDZENIE;
		if (bmi >= 17 && bmi < 18.5)
			return BmiRate.NIEDOWAGA;
		if (bmi >= 25 && bmi < 30)
			return BmiRate.NADWAGA;
		if (bmi >= 30 && bmi < 35)
			return BmiRate.I_ST_OTYŁOŚCI;
		if (bmi >= 35 && bmi < 40)
			return BmiRate.II_ST_OTYŁOŚCI;
		if (bmi > 40)
			return BmiRate.OTYŁOŚĆ_SKRAJNA;
		return BmiRate.PRAWIDŁOWE;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer roleId) {
		this.role = roleId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", gender=" + gender + ", age=" + age
				+ ", height=" + height + ", weight=" + weight + ", jobActivityLevel=" + jobActivityLevel + ", role="
				+ role + "]";
	}
}
