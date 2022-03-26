package com.cts.vehicle.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Help {

	@Id
	private Integer requestId;
	private Integer userId;
	private String issue;
	private String description;
	private String dateOfTicket;
	private String resolveRequest;

	public Help() {
		super();
	}

	public Help(Integer requestId, Integer userId, String issue, String description, String dateOfTicket,
			String resolveRequest) {
		super();
		this.requestId = requestId;
		this.userId = userId;
		this.issue = issue;
		this.description = description;
		this.dateOfTicket = dateOfTicket;
		this.resolveRequest = resolveRequest;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateOfTicket() {
		return dateOfTicket;
	}

	public void setDateOfTicket(String dateOfTicket) {
		this.dateOfTicket = dateOfTicket;
	}

	public String getResolveRequest() {
		return resolveRequest;
	}

	public void setResolveRequest(String resolveRequest) {
		this.resolveRequest = resolveRequest;
	}

	@Override
	public String toString() {
		return "Help [requestId=" + requestId + ", userId=" + userId + ", issue=" + issue + ", description="
				+ description + ", dateOfTicket=" + dateOfTicket + ", resolveRequest=" + resolveRequest + "]";
	}

	
	

	
}