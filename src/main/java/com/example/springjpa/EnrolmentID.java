package com.example.springjpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnrolmentID implements Serializable{
	
	@Column(name = "student_id")
	private long studentId;
	
	@Column(name = "course_id")
	private long courseId;

	public EnrolmentID(long studentId, long courseId) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public EnrolmentID() {
		super();
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (courseId ^ (courseId >>> 32));
		result = prime * result + (int) (studentId ^ (studentId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnrolmentID other = (EnrolmentID) obj;
		if (courseId != other.courseId)
			return false;
		if (studentId != other.studentId)
			return false;
		return true;
	}

	
}
