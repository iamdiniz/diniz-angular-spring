import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SharedModule } from '../shared/shared.module';
import { CoursesListComponent } from './components/courses-list/courses-list.component';
import { CourseFormComponent } from './containers/course-form/course-form.component';
import { CoursesComponent } from './containers/courses/courses.component';
import { CoursesRoutingModule } from './courses-routing.module';

@NgModule({
  declarations: [
    CoursesComponent,
    CourseFormComponent,
    CoursesListComponent
  ],
  imports: [
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    AppMaterialModule,
    CommonModule,
    CoursesRoutingModule,
    SharedModule
  ]
})
export class CoursesModule { }
