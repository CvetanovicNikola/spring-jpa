package com.example.springjpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity(name = "StudentIDCard")
@Table(name = "student_id_card",
uniqueConstraints = {
		@UniqueConstraint(name="student_id_card_number_unique", columnNames = "card_number")
}
		)
public class StudentIdCard {
	@Id
	@SequenceGenerator(
			name = "student_id_card_sequence",
			sequenceName="student_id_card_sequence",
			allocationSize=1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_id_card_sequence"
			)
	
	@Column(
			name = "id",
			nullable = false
			)
	private long id;
	
	@Column(
			name = "card_number",
			nullable = false,
			length = 15			
			)
	private String cardNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(
			name = "student_id",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name="student_id_fk")
			)
	
	private Student student;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public StudentIdCard(long id, String cardNumber) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
	}

	public StudentIdCard() {
		super();
	}

	public StudentIdCard(String cardNumber, Student student) {
		super();
		this.cardNumber = cardNumber;
		this.student = student;
	}
	
	

}
