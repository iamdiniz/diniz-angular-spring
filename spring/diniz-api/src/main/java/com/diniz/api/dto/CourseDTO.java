 package com.diniz.api.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.diniz.domain.enums.Category;
import com.diniz.domain.enums.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
		@JsonProperty("_id") Long id,
		@NotBlank @NotNull @Length(min = 5, max = 100) String name,
		@NotNull @Length(max = 10) @ValueOfEnum(enumClass = Category.class) String category,
		@NotNull @NotEmpty @Valid List<LessonDTO> lessons) {

}
