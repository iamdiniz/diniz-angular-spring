package com.diniz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.diniz.domain.model.Course;
import com.diniz.domain.repository.CourseRepository;

@SpringBootApplication
public class DinizApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DinizApiApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initDataBase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course newCourse = new Course();
			newCourse.setName("Angular");
			newCourse.setCategory("Front-end");

			courseRepository.save(newCourse);
		};
	}

}
