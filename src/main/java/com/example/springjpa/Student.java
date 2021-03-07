package com.example.springjpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name = "Student")
@Table(name = "student",
	uniqueConstraints = {
			@UniqueConstraint(name="student_email_unique", columnNames = "email")
	}
		)
public class Student {
	@Id
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName="student_sequence",
			allocationSize=1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
			)
	
	@Column(
			name = "id",
			nullable = false
			)
	private long id;
	
	@Column(
			name = "first_name",
			nullable = false,
			columnDefinition = "TEXT"
			)
	private String firstName;
	
	@Column(
			name = "last_name",
			nullable = false,
			columnDefinition = "TEXT"
			)
	private String lastName;
	
	@Column(
			name = "email",
			nullable = false,
			columnDefinition = "TEXT"
			)
	private String email;
	
	@Column(name = "age")
	private int age;
	
	@OneToOne(
			mappedBy = "student",
			orphanRemoval = true,
			cascade = CascadeType.ALL
			)
	private StudentIdCard studentIdCard;
			
	public StudentIdCard getStudentIdCard() {
		return studentIdCard;
	}

	public void setStudentIdCard(StudentIdCard studentIdCard) {
		this.studentIdCard = studentIdCard;
	}

	@OneToMany(
			mappedBy = "student",
			orphanRemoval = true,
			cascade = CascadeType.ALL
			)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Book> books = new ArrayList<Book>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "student"
			)
	private List<Enrolment> enrolments = new ArrayList<>();
//	@ManyToMany(
//			cascade = CascadeType.ALL
//			)
//	@JoinTable(
//			name = "enrolment",
//			joinColumns = @JoinColumn(
//						name = "student_id",
//						foreignKey = @ForeignKey(name = "enrolement_student_id_fk")
//								), 
//								inverseJoinColumns = @JoinColumn(
//								name = "course_id",
//								foreignKey = @ForeignKey(
//											name = "enrolement_course_id_fk"
//										)
//					)
//			)
//	private List<Course> courses = new ArrayList<Course>();
	
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

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		if(!this.books.contains(book)) {
			this.books.add(book);
			book.setStudent(this);
		}
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void removeBook(Book book) {
		if(books.contains(book)) {
			books.remove(book);
			book.setStudent(null);
		}
	}
	public Student(String firstName, String lastName, String email, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}

	public Student() {
		
	}	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", age=" + age + "]";
	}
	
	
	
}
