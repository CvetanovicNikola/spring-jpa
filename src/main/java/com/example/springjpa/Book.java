package com.example.springjpa;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Book")
@Table(name = "book")
public class Book {
	@Id
	@SequenceGenerator(
			name = "book_sequence",
			sequenceName = "book_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			generator = "book_sequence",
			strategy = GenerationType.SEQUENCE
			)
	@Column(
			name = "id",
			updatable = false
			)
	private int id;
	
	@Column(
			name = "created_at",
			nullable = false,
			columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
			)
	private LocalDate createdAt;
	
	@Column(
			name = "book_name",
			nullable = false
			)
	private String bookName;
	
	@ManyToOne
	@JoinColumn(
			name = "student_id",
			nullable = false,
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name="student_book_fk")
			)
	
	private Student student;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", createdAt=" + createdAt + ", bookName=" + bookName + ", student=" + student + "]";
	}

	public Book(int id, LocalDate createdAt, String bookName, Student student) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.bookName = bookName;
		this.student = student;
	}

	public Book(LocalDate createdAt, String bookName, Student student) {
		super();
		this.createdAt = createdAt;
		this.bookName = bookName;
		this.student = student;
	}
	

	public Book(String bookName, LocalDate createdAt) {
		super();
		this.createdAt = createdAt;
		this.bookName = bookName;
	}
	
	public Book() {
		super();
	}
	
}
