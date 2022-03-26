package com.cts.vehicle.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Policy {

	
	@Id
	private Integer policyId;
	private String policyName;
	private String featuresAndBenefits;
	private Integer tariffRates;
	private Integer premiumRates;
	public Policy() {
		super();
	}
	public Policy(Integer policyId, String policy, String featuresAndBenefits, Integer tariffRates,
			Integer premiumRates) {
		super();
		this.policyId = policyId;
		this.policyName = policy;
		this.featuresAndBenefits = featuresAndBenefits;
		this.tariffRates = tariffRates;
		this.premiumRates = premiumRates;
	}
	public Integer getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policy) {
		this.policyName = policy;
	}
	public String getFeaturesAndBenefits() {
		return featuresAndBenefits;
	}
	public void setFeaturesAndBenefits(String featuresAndBenefits) {
		this.featuresAndBenefits = featuresAndBenefits;
	}
	public Integer getTariffRates() {
		return tariffRates;
	}
	public void setTariffRates(Integer tariffRates) {
		this.tariffRates = tariffRates;
	}
	public Integer getPremiumRates() {
		return premiumRates;
	}
	public void setPremiumRates(Integer premiumRates) {
		this.premiumRates = premiumRates;
	}
	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", featuresAndBenefits=" + featuresAndBenefits
				+ ", tariffRates=" + tariffRates + ", premiumRates=" + premiumRates + "]";
	}
	
	
}
