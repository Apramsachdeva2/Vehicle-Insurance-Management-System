package com.cts.vehicle.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", contactNumber=" + contactNumber + ", email=" + email
				+ ", licenseNumber=" + licenseNumber + ", userName=" + userName + ", password=" + password
				+ ", designation=" + designation + ", role=" + role + ", userCategory=" + userCategory + ", status="
				+ status + ", secretQuestion=" + secretQuestion + ", secretAnswer=" + secretAnswer + "]";
	}

	@Id
	private Integer userId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String contactNumber;
	private String email;
	private String licenseNumber;
	private String userName;
	private String password;
	private String designation;
	private String role;
	private String userCategory;
	private String status;
	private String secretQuestion;
	private String secretAnswer;
	
	public User() {
		super();
	}

	public User(Integer userId, String firstName, String lastName, String dateOfBirth, String gender,
			String contactNumber, String email, String licenseNumber, String userName, String password,
			String designation, String role, String userCategory, String status, String secretQuestion,
			String secretAnswer) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.email = email;
		this.licenseNumber = licenseNumber;
		this.userName = userName;
		this.password = password;
		this.designation = designation;
		this.role = role;
		this.userCategory = userCategory;
		this.status = status;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	
}
