package com.eaa.springdatajpatutorial.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eaa.springdatajpatutorial.model.Course;
import com.eaa.springdatajpatutorial.model.CourseMaterial;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseMaterialRepositoryTest {
	
	@Autowired
	private CourseMaterialRepository repository; 
	
	@Test
	public void saveCourseMaterial(){
		Course course = Course.builder().title("DSA").credit(6).build();
		CourseMaterial courseMaterial = CourseMaterial.builder().url("www.google.com").course(course).build();
		repository.save(courseMaterial);
	}

	@Test
	public void printAllCoursMaterials(){
		List<CourseMaterial> courseMaterials = repository.findAll();
		System.out.println("--courseMaterials--"+courseMaterials);
		
		//--courseMaterials--[CourseMaterial(courseMaterialId=2, url=www.google.com, credit=null), CourseMaterial(courseMaterialId=3, url=www.google.com, credit=null)]
        // FetchType lazy we are excluding course data, ++ ToString
		
		
	}
}
