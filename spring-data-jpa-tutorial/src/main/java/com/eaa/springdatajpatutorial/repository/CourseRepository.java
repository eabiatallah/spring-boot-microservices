package com.eaa.springdatajpatutorial.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eaa.springdatajpatutorial.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	List<Course> findByTitleContaining(String title, Pageable firstPageTenRecords);

}
