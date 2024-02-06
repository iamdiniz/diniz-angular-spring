import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';

import { CoursesService } from '../services/courses.service';
import { Course } from '../model/course';

export const courseResolver: ResolveFn<any> = (route, state) => {

  const course: Course = {
    _id: '',
    name: '',
    category: '',
    lessons: []
  }

  if (route.params && route.params['id']) {
    return inject(CoursesService).findById(route.params['id']);
  }

  return course;
};
