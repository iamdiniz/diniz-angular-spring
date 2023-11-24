import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  courses: Course [] = [
    { _id: '1', name: 'Angular 2', category: 'Angular' },
  ];
  
  displayedColumns = ['name', 'category'];

  constructor() { }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

}
