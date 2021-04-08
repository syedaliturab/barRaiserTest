package com.springTest.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Quizrelation")
public class QuizQuestions {
	@Column(name="quiztype_id")
	private int quiztype;
	@Column(name="question_id")
	private int questionid;
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

}
