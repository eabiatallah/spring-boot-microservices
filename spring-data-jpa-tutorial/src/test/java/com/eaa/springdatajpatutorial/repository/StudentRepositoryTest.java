package com.eaa.springdatajpatutorial.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eaa.springdatajpatutorial.model.Guardian;
import com.eaa.springdatajpatutorial.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
// @DataJpaTest // Used to not save data in DB
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void saveStudent(){
		Student student = Student.builder()
				.firstName("Elias")
				.emailId("eabiatallah@gmail.com")
				.lastName("AbiAtallah")
				//.guardianName("Eli")
				//.guardianMobile("9999999999")
				.build();
		
		studentRepository.save(student);
	}
	
	@Test
	public void saveStudentWithGuardian(){
		Guardian guardian = Guardian.builder().email("guard@gmail.com").name("test").mobile("99999").build();
		Student student = Student.builder()
				.firstName("EAA")
				.emailId("eabiatallah1@gmail.com")
				.guardian(guardian)
				.build();
		
		studentRepository.save(student);
	}
	
	@Test
	public void printAllStudent(){
		List<Student> studentList = studentRepository.findAll();
		System.out.println("--- studentList --- "+studentList);
	}
	
	@Test
	public void printStudentByFirstNameTest(){
		List<Student> students = studentRepository.findByFirstName("Elias");
		System.out.println("--- students --- "+students);
	}
	
	@Test
	public void printStudentByContainingFirstName(){
		List<Student> students = studentRepository.findByFirstNameContaining("E");
		System.out.println("--- students --- "+students);
	}
	
	@Test
	public void printStudentByGuardianName(){
		List<Student> guardians = studentRepository.findByGuardianName("test");
		System.out.println("--- guardians --- "+guardians);
	}
	
	@Test
	public void getStudentByEmailAddressTest(){
		Student student = studentRepository.getStudentByEmailAddress("eabiatallah@gmail.com");
		System.out.println("--- student --- "+student);
	}
	
	@Test
	public void getStudentFirstNameByEmailAddressTest(){
		String studentFirstName = studentRepository.getStudentFirstNameByEmailAddress("eabiatallah@gmail.com");
		System.out.println("--- studentFirstName --- "+studentFirstName);
	}
	
	@Test
	public void getStudentByEmailAddressNativeTest(){
		Student student = studentRepository.getStudentByEmailAddressNative("eabiatallah@gmail.com");
		System.out.println("--- student --- "+student);
	}
	
	@Test
	public void getStudentByEmailAddressNativeNamedParamTest(){
		Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("eabiatallah@gmail.com");
		System.out.println("--- student --- "+student);
	}
	
	@Test
	public void updateStudentNameByEmailAddressTest(){
		studentRepository.updateStudentNameByEmailId("test_one", "eabiatallah1@gmail.com");
	}
	
	
}
