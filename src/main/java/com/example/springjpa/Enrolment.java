package com.example.springjpa;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "Enrolment")
@Table(name = "enrolment")
public class Enrolment {
	
	@EmbeddedId
	private EnrolmentID id;
	
	@ManyToOne
	@MapsId("studentId")
	@JoinColumn(
			name = "student_id",
			foreignKey = @ForeignKey(name="student_id_fk")
			)
	private Student student;
	
	@ManyToOne
	@MapsId("courseId")
	@JoinColumn(
			name = "course_id",
			foreignKey = @ForeignKey(name="course_id_fk")
			)
	private Course course;
	
	@Column(
			name = "created_at",
			nullable = false,
			columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
			)
	private LocalDate createdAt;
		
	public Enrolment(Student student, Course course, LocalDate createdAt) {
		super();
		this.student = student;
		this.course = course;
		this.createdAt = createdAt;
	}

	public Enrolment(EnrolmentID id, Student student, Course course, LocalDate createdAt) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.createdAt = createdAt;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Enrolment [id=" + id + ", student=" + student + ", course=" + course + "]";
	}

	public Enrolment(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}
	
	public Enrolment(EnrolmentID id, Student student, Course course) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
	}

	public Enrolment() {
		super();
	}

	public EnrolmentID getId() {
		return id;
	}

	public void setId(EnrolmentID id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
