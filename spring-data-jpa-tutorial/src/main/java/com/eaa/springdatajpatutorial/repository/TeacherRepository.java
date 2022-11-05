package com.eaa.springdatajpatutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eaa.springdatajpatutorial.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
