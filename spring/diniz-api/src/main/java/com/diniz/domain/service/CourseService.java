package com.diniz.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.diniz.api.dto.CourseDTO;
import com.diniz.api.dto.mapper.CourseMapper;
import com.diniz.domain.enums.Category;
import com.diniz.domain.exception.RecordNotFoundException;
import com.diniz.domain.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {
	
	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;

	public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
		this.courseMapper = courseMapper;
		this.courseRepository = courseRepository;
	}
	
	public List<CourseDTO> findAll() {
		return courseRepository.findAll().stream()
				.map(courseMapper::toDTO)
					.collect(Collectors.toList());
	}
	
	public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
		return courseRepository.findById(id)
				.map(courseMapper::toDTO)
					.orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public CourseDTO create(@Valid @NotNull CourseDTO newCourse) {
		return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(newCourse)));
	}
	
	public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id)
                .map(entityCaseExist -> {
                    entityCaseExist.setName(course.name());
                    entityCaseExist.setCategory(Category.FRONT_END);
                    return courseMapper.toDTO(courseRepository.save(entityCaseExist));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
	
	public void delete(@PathVariable @NotNull @Positive Long id) {
		courseRepository.delete(courseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(id)));
	}

}
