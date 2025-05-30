package com.mourat.udemy.hibernatedemo;

import com.mourat.udemy.hibernatedemo.dao.StudentDAO;
import com.mourat.udemy.hibernatedemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HibernatedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernatedemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO dao){
		return runner -> {
			createMultipleStudents(dao);
		};
	}

	// a different version of method than tutorial
	public void createMultipleStudents(StudentDAO dao){

		// create 3 students and save them
		createStudent(dao, "Mourat", "Achoi", "mayrit91@gmail.com");
		createStudent(dao, "Konstantinos", "Vardakis", "kvar@gmail.com");
		createStudent(dao, "Seyran", "Osman", "toPontiki@gmail.com");
		createStudent(dao, "Sali", "Kotza Hasan", "khsali@gmail.com");
	}

	private void createStudent(StudentDAO dao, String fName, String lName, String email){

		// create the student object
		Student student = new Student(fName, lName, email);

		// save the student object
		dao.save(student);

		//display the id of the created student
		System.out.println("Student created with ID: " + student.getId());
	}

	private void readStudentByID(StudentDAO dao, int id){
		System.out.println("The student data with id " + id + ":");
		System.out.println(dao.findById(id));
	}

	private void readAllStudents(StudentDAO dao){

		// get list of students
		List<Student> students = dao.findAll();

		// display all students
		for(Student s : students){
			System.out.println(s);
		}
	}

	private void getStudentsWithLastName(StudentDAO dao, String lName){
		// get the list of students with the given last name
		List<Student> students = dao.findByLastName(lName);

		// display all students in the list
		for(Student s : students){
			System.out.println(s);
		}
	}

	private void updateStudentWithID(StudentDAO dao, int id, String fName){

		//find the student with the given id
		Student student = dao.findById(id);

		// make changes on student
		student.setFirstName(fName);
		dao.update(student);

		// display student after changes
		System.out.println(student);
	}

	private void deleteStudentsWithLastName(StudentDAO dao, String lName){
		// delete and show the count of deleted students
		System.out.println("Quantity of deleted students with last name " + lName + " is " + dao.deleteAllWith(lName));
	}

	private void deleteStudentWithId(StudentDAO dao, int id){
		dao.delete(id);
		System.out.println("Student with id " + id + " is removed!");
	}

	private void deleteAllStudents(StudentDAO dao){
		System.out.println("Number of deleted students is: " + dao.deleteAll());
	}
}
