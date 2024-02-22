package com.diniz.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.diniz.api.dto.CourseDTO;
import com.diniz.api.dto.CoursePageDTO;
import com.diniz.api.dto.mapper.CourseMapper;
import com.diniz.domain.exception.RecordNotFoundException;
import com.diniz.domain.model.Course;
import com.diniz.domain.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
public class CourseService {
	
	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;

	public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
		this.courseMapper = courseMapper;
		this.courseRepository = courseRepository;
	}
	
	public CoursePageDTO list(@PositiveOrZero int page, @Positive @Max(100) int pageSize) {
		Page<Course> pageCourse = courseRepository.findAll(PageRequest.of(page, pageSize));
		List<CourseDTO> courses = pageCourse.get().map(courseMapper::toDTO).collect(Collectors.toList());
		return new CoursePageDTO(courses, pageCourse.getTotalElements(), pageCourse.getTotalPages());
	}
	
	public CourseDTO findById(@NotNull @Positive Long id) {
		return courseRepository.findById(id)
				.map(courseMapper::toDTO)
					.orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public CourseDTO create(@Valid @NotNull CourseDTO newCourse) {
		return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(newCourse)));
	}
	
	public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
        return courseRepository.findById(id)
                .map(entityCaseExist -> {
                	Course course = courseMapper.toEntity(courseDTO);
                    entityCaseExist.setName(courseDTO.name());
                    entityCaseExist.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));
                    entityCaseExist.getLessons().clear();
                    course.getLessons().forEach(entityCaseExist.getLessons()::add);
                    return courseMapper.toDTO(courseRepository.save(entityCaseExist));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
	
	public void delete(@NotNull @Positive Long id) {
		courseRepository.delete(courseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(id)));
	}
}
