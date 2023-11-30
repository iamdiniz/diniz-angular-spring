package com.diniz.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diniz.domain.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
