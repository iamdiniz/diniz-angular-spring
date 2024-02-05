package com.diniz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.diniz.domain.enums.Category;
import com.diniz.domain.model.Course;
import com.diniz.domain.model.Lesson;
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
			newCourse.setCategory(Category.FRONT_END);
			
			Lesson newLesson = new Lesson();
			newLesson.setName("Introdução");
			newLesson.setYoutubeUrl("watch?v=1");
			newLesson.setCourse(newCourse);
			newCourse.getLessons().add(newLesson);
			
			Lesson newLesson2 = new Lesson();
			newLesson2.setName("Angular");
			newLesson2.setYoutubeUrl("watch?v=2");
			newLesson2.setCourse(newCourse);
			newCourse.getLessons().add(newLesson2);

			courseRepository.save(newCourse);
		};
	}

}
