package com.eaa.springdatajpatutorial.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eaa.springdatajpatutorial.model.Student;

//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	public List<Student> findByFirstName(String firstName);
	
	public List<Student> findByFirstNameContaining(String name);
	
	public List<Student> findByLastNameNotNull();

	public List<Student> findByGuardianName(String guardianName);
	
	Student findByFirstNameAndLastName(String firstName, String lastName);
	
	//JPQL: property in class not DB
	@Query("select s from Student s where s.emailId = ?1")
	Student getStudentByEmailAddress(String emailId);
	
	@Query("select s.firstName from Student s where s.emailId = ?1")
	String getStudentFirstNameByEmailAddress(String emailId);
	
	
	// Native Query, used for complex queries
	@Query(value = "SELECT * FROM tbl_student s where s.email_address = ?1",
			nativeQuery = true)
	Student getStudentByEmailAddressNative(String emailId);
	
	//Native NamedParam
	//Native Query same as DB
	@Query(value = "SELECT * FROM tbl_student s where s.email_address = :emailId",
			nativeQuery = true)
	Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);
	
	@Modifying
	@Transactional
	@Query(value = "update tbl_student set first_name = ?1 where email_address = ?2",
			nativeQuery = true)
	int updateStudentNameByEmailId(String firstName, String emailId);

}
