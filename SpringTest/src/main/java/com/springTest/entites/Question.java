package com.springTest.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Quiz")
public class Question {
	@Column(name="question")
private String question;
	@Column(name="answer")
private String answer;
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Question() {
		
	}
	public Question(String question, String answer) {
	
		this.question = question;
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Question [question=" + question + ", answer=" + answer + ", id=" + id + "]";
	}
	
	

}
