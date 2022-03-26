package com.cts.vehicle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PolicyAssign {
	
	@Id
	@GeneratedValue
	private Integer id;
	private Integer fieldOfficerId;
	private Integer policyId;
	private Integer policyAdminId;
	private String status;

	
	public PolicyAssign(Integer id, Integer fieldOfficerId, Integer policyId, Integer policyAdminId, String status) {
		super();
		this.id = id;
		this.fieldOfficerId = fieldOfficerId;
		this.policyId = policyId;
		this.policyAdminId = policyAdminId;
		this.status = status;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public PolicyAssign() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getFieldOfficerId() {
		return fieldOfficerId;
	}


	public void setFieldOfficerId(Integer fieldOfficerId) {
		this.fieldOfficerId = fieldOfficerId;
	}


	public Integer getPolicyId() {
		return policyId;
	}


	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}


	public Integer getPolicyAdminId() {
		return policyAdminId;
	}


	public void setPolicyAdminId(Integer policyAdminId) {
		this.policyAdminId = policyAdminId;
	}


	@Override
	public String toString() {
		return "PolicyAssign [id=" + id + ", fieldOfficerId=" + fieldOfficerId + ", policyId=" + policyId
				+ ", policyAdminId=" + policyAdminId + ", status=" + status + "]";
	}


	}