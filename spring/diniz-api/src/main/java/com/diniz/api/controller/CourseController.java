package com.diniz.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.diniz.domain.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("api/courses")
@AllArgsConstructor
public class CourseController {
	
	private final CourseRepository courseRepository;

	@GetMapping
	public List<Course> findAll() {
		return courseRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> findById(@PathVariable @NotNull @Positive Long id) {
		return courseRepository.findById(id)
				.map(entityCaseExist -> ResponseEntity.ok().body(entityCaseExist))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Course create(@RequestBody @Valid Course newCourse) {
		return courseRepository.save(newCourse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id,
			@RequestBody @Valid Course editCourse) {
		return courseRepository.findById(id)
				.map(entityCaseExist -> {
					entityCaseExist.setName(editCourse.getName());
					entityCaseExist.setCategory(editCourse.getCategory());
					Course updatedCourse = courseRepository.save(entityCaseExist);
					return ResponseEntity.ok().body(updatedCourse);
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
		return courseRepository.findById(id)
				.map(entityCaseExist -> {
					courseRepository.deleteById(id);
					return ResponseEntity.noContent().<Void>build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

}
