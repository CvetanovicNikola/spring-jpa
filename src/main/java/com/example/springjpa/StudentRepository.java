package com.example.springjpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	@Query(
			value="SELECT * FROM student s WHERE s.email = ?1", 
			nativeQuery=true)
	Optional<Student> findStudentByEmail(String email);
	
	@Query(
			value="select * from student s where s.first_name = :firstName and s.age >= :age", 
			nativeQuery = true)
	List<Student> findStudentByFirstNameEqualsAndAgeEquals(
			@Param("firstName")String firstName, 
			@Param("age") int age);
	
	
	@Transactional
	@Modifying
	@Query(value="delete from student s where s.id = :id", nativeQuery = true)
	int deleteStudentById(@Param("id") long id);
}
