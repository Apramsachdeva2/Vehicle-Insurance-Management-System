package com.cts.vehicle.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Feedback {

	@Id
	private int ansId;
	private int quesId;
	private String question;
	private String answer;
	private int userId;
	
	
	
	
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Feedback [ansId=" + ansId + ", quesId=" + quesId + ", question=" + question + ", answer=" + answer
				+ ", userId=" + userId + "]";
	}
	public int getAnsId() {
		return ansId;
	}
	public void setAnsId(int ansId) {
		this.ansId = ansId;
	}
	public int getQuesId() {
		return quesId;
	}
	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
