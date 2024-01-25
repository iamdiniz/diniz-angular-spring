package com.diniz.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.diniz.domain.exception.RecordNotFoundException;
import com.diniz.domain.model.Course;
import com.diniz.domain.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {
	
	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public List<Course> findAll() {
		return courseRepository.findAll();
	}
	
	public Course findById(@PathVariable @NotNull @Positive Long id) {
		return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public Course create(@Valid Course newCourse) {
		return courseRepository.save(newCourse);
	}
	
	public Course update(@NotNull @Positive Long id, @Valid Course editCourse) {
		return courseRepository.findById(id)
				.map(entityCaseExist -> {
					entityCaseExist.setName(editCourse.getName());
					entityCaseExist.setCategory(editCourse.getCategory());
					return courseRepository.save(entityCaseExist);
				}).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public void delete(@PathVariable @NotNull @Positive Long id) {
		courseRepository.delete(courseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(id)));
	}

}
