package com.diniz.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.diniz.domain.model.Course;
import com.diniz.domain.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("api/courses")
public class CourseController {
	
	private final CourseService courseService;
	
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	public List<Course> findAll() {
		return courseService.findAll();
	}
	
	@GetMapping("/{id}")
	public Course findById(@PathVariable @NotNull @Positive Long id) {
		return courseService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Course create(@RequestBody @Valid Course newCourse) {
		return courseService.create(newCourse);
	}
	
	@PutMapping("/{id}")
	public Course update(@PathVariable @NotNull @Positive Long id,
			@RequestBody @Valid Course editedCourse) {
		return courseService.update(id, editedCourse);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @NotNull @Positive Long id) {
		courseService.delete(id);
	}

}
