package com.eaa.springdatajpatutorial.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eaa.springdatajpatutorial.model.Course;
import com.eaa.springdatajpatutorial.model.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherRepositoryTest {
	
	@Autowired
	private TeacherRepository repository;
	
//	@Test
//	public void saveTeacher(){
//		Course courseDBA = Course.builder().title("DBA").credit(5).build();
//		Course courseJava= Course.builder().title("Java").credit(6).build();
//		Teacher teacher = Teacher.builder().firstName("Elias").lastName("Khan").courses(Arrays.asList(courseDBA,courseJava)).build();
//		repository.save(teacher);
//	}

}
