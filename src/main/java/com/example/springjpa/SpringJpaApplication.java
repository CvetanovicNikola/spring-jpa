package com.example.springjpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import com.github.javafaker.Faker;

@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
			StudentIdCardRepository studentCardRepository
			) {
		return args ->{
			Faker faker = new Faker();
			String firstname = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@g.com", firstname, lastName);
			Student student = new Student(
					firstname,
					lastName,
					email,
					faker.number().numberBetween(17, 60)
					);
			StudentIdCard card = new StudentIdCard("12345678", student);
			
			student.addBook(new Book("Lord of the rings", LocalDate.now().minusDays(2)));
			student.addBook(new Book("Lord of the rings 2", LocalDate.now().minusDays(2)));
			student.addBook(new Book("Lord of the rings 3", LocalDate.now().minusDays(2)));
			
			student.setStudentIdCard(card);
			
			studentRepository.save(student);
			
			//studentCardRepository.save(card);
			
			studentRepository.findById(1L).ifPresent(s -> {
				List<Book> books = s.getBooks();
				books.forEach(b -> System.out.println(s.getFirstName() + b.getBookName()));
			});
			
			//studentCardRepository.findById(1L).ifPresent(System.out::println);
			
			//studentRepository.deleteById(173L);
		
		};



	} 
	private void fakeStudents(StudentRepository studentRepository) {
		Faker faker = new Faker();
		for(int i=0;i<21;i++) {
			String firstname = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@g.com", firstname, lastName);
			Student student = new Student(
					firstname,
					lastName,
					email,
					faker.number().numberBetween(17, 60)
					);
			studentRepository.save(student);
		}
		PageRequest pageRequest = PageRequest.of(0, 5);
		Page<Student> page = studentRepository.findAll(pageRequest);
		page.get().forEach(s ->System.out.println(s));
		

};
}