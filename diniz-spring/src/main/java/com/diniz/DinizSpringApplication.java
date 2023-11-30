package com.diniz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.diniz.domain.model.Course;
import com.diniz.domain.repository.CourseRepository;

@SpringBootApplication
public class DinizSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DinizSpringApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initDataBase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();
			
			Course newCourse = new Course();
			newCourse.setName("Angular");
			newCourse.setCategory("front-end");
			
			courseRepository.save(newCourse);
		};
	}

}
