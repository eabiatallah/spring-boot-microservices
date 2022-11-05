package com.eaa.springdatajpatutorial.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.eaa.springdatajpatutorial.model.Course;
import com.eaa.springdatajpatutorial.model.Student;
import com.eaa.springdatajpatutorial.model.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository repository; 
	
	@Test
	public void printCourses(){
		List<Course> courses = repository.findAll();
		System.out.println("-- courses --"+courses);
		
		//-- courses --[Course(courseId=1, title=DSA, credit=6), Course(courseId=2, title=DSA, credit=6)]
        // We don't have reference to course material because our relation is unidirectional
		
		//After adding courseMaterial bidirectional in Course Class / @OneToOne(mappedBy = "course")
		// -- courses --[Course(courseId=1, title=DSA, credit=6, courseMaterial=CourseMaterial(courseMaterialId=2, url=www.google.com, credit=null)), Course(courseId=2, title=DSA, credit=6, courseMaterial=CourseMaterial(courseMaterialId=3, url=www.google.com, credit=null))]

	}
	
	@Test
	public void saveCourseWithTeacher(){
		Teacher teacher = Teacher.builder().firstName("Elissar").lastName("Azouri").build();
		Course course = Course.builder().title("Pyhton").credit(6).teacher(teacher).build();
		repository.save(course);
	}
	
	@Test
	public void findAllPagination(){
		Pageable firstPagewith3Records = PageRequest.of(0, 3);
		Pageable secondPagewith2Records = PageRequest.of(1, 2);
		
		List<Course> courses = repository.findAll(firstPagewith3Records).getContent();
		
		long totalElements = repository.findAll(firstPagewith3Records).getTotalElements(); 
		long totalPages = repository.findAll(firstPagewith3Records).getTotalPages();
		
		System.out.println("--courses--"+courses);
		System.out.println("--totalElements--"+totalElements);
		System.out.println("--totalPages--"+totalPages);
	}
	
	@Test
	public void findAllSorting(){
		Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));
		Pageable sortByCredit =  PageRequest.of(0, 2, Sort.by("credit").descending());
		Pageable sortByTitleAndCredit =  PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));
		
		List<Course> courses = repository.findAll(sortByTitle).getContent();
		List<Course> creddits = repository.findAll(sortByCredit).getContent();
		List<Course> both = repository.findAll(sortByTitleAndCredit).getContent();
		
		System.out.println("--courses--"+courses);
		System.out.println("--creddits--"+creddits);
		System.out.println("--both--"+both);
	}
	
	@Test
	public void printFindByTitleContaining(){
		Pageable firstPageTenRecords = PageRequest.of(0, 10);
		List<Course> courses = repository.findByTitleContaining("D", firstPageTenRecords);
		System.out.println("---printFindByTitleContaining---"+courses);
	}
	
	@Test
	public void saveCourseWithStudentAndTeacher(){
		Teacher teacher = Teacher.builder().firstName("Jaber").lastName("Jaber").build();
		Course course = Course.builder().title("AI").credit(12).teacher(teacher).build();
		Student student = Student.builder().firstName("EE").lastName("AA").emailId("ea@gmail.com").build();
		course.addStudents(student);
		repository.save(course);
	}
}
