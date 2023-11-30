package com.diniz.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diniz.domain.model.Course;
import com.diniz.domain.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
	
	private final CourseRepository courseRepository;

	@GetMapping
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

}
