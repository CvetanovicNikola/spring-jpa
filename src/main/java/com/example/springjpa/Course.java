package com.example.springjpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name = "Course")
@Table(name = "course")
public class Course {
	
	@Id
	@SequenceGenerator(
			name = "course_sequence",
			sequenceName="course_sequence",
			allocationSize=1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "course_sequence"
			)
	
	@Column(
			name = "id",
			nullable = false
			)
	private long id;
	
	@Column(
			name = "name",
			nullable = false,
			columnDefinition = "TEXT"
			)
	private String name;
	
	@Column(
			name = "department",
			nullable = false,
			columnDefinition = "TEXT"
			)
	private String department;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(
			mappedBy = "course",
			cascade = CascadeType.ALL
			)
	private List<Enrolment> enrolments = new ArrayList<>();

	public void addEnrolment (Enrolment enrolment) {
		if(!enrolments.contains(enrolment))
		enrolments.add(enrolment);
	}
	
	public void reoveEnrolment(Enrolment enrolment) {
		enrolments.remove(enrolment);
	}
	
	public List<Enrolment> getEnrolments() {
		return enrolments;
	}

	public void setEnrolments(List<Enrolment> enrolments) {
		this.enrolments = enrolments;
	}
	
	public Course(long id, String name, String department) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
	}

	public Course(String name, String department) {
		super();
		this.name = name;
		this.department = department;
	}

	public Course() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", department=" + department + "]";
	}
	
	
}
