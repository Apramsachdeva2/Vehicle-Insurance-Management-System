package com.cts.vehicle.model;



import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

@Entity
public class Insurance {

	@Id
	private Integer insuranceId;
	private Integer userId;
	private String firstName;
	private String lastName;
	private String address;
	private String vehicleRegistrationNumber;
	private String mobileNumber;
	private String emailId;
	private String cityOfRegistration;
	private String registrationDate;
	private String make;
	private String model;
	private String variant;
	private Integer manufacturingYear;
	private String fuelType;
	private int policyId;


	private String expireDate;
	private String feedBackText;
	@Value("Customer submitted")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Insurance() {
		super();
	}

	

	public Insurance(Integer insuranceId, Integer userId, String firstName, String lastName, String address,
			String vehicleRegistrationNumber, String mobileNumber, String emailId, String cityOfRegistration,
			String registrationDate, String make, String model, String variant, Integer manufacturingYear,
			String fuelType, int policyId, String expireDate, String feedBackText, String status) {
		super();
		this.insuranceId = insuranceId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.cityOfRegistration = cityOfRegistration;
		this.registrationDate = registrationDate;
		this.make = make;
		this.model = model;
		this.variant = variant;
		this.manufacturingYear = manufacturingYear;
		this.fuelType = fuelType;
		this.policyId = policyId;
		this.expireDate = expireDate;
		this.feedBackText = feedBackText;
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer uId) {
		this.userId = uId;
	}

	public Integer getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCityOfRegistration() {
		return cityOfRegistration;
	}

	public void setCityOfRegistration(String cityOfRegistration) {
		this.cityOfRegistration = cityOfRegistration;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public Integer getManufacturingYear() {
		return manufacturingYear;
	}

	public void setManufacturingYear(Integer manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}


	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public String toString() {
		return "Insurance [insuranceId=" + insuranceId + ", userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", vehicleRegistrationNumber="
				+ vehicleRegistrationNumber + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId
				+ ", cityOfRegistration=" + cityOfRegistration + ", registrationDate=" + registrationDate + ", make="
				+ make + ", model=" + model + ", variant=" + variant + ", manufacturingYear=" + manufacturingYear
				+ ", fuelType=" + fuelType + ", policyId=" + policyId + ", expireDate=" + expireDate + ", feedBackText="
				+ feedBackText + ", status=" + status + "]";
	}

	public String getFeedBackText() {
		return feedBackText;
	}

	public void setFeedBackText(String feedBackText) {
		this.feedBackText = feedBackText;
	}

}
