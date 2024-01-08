package com.springrest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.services.CourseService;
@CrossOrigin(origins = "http://localhost:3000") 
@RestController
public class MyController {
	
	@Autowired
	private CourseService courseservice;
	
	@GetMapping("/home")
	public String Home() {
		return "this is home controller";
	}

	@GetMapping("/courses")
	public List<Course> getCourses(){
		return this.courseservice.getCourses();
	}
	
	@GetMapping("/courses/{courseId}")
	public ResponseEntity<Course> getCourse(@PathVariable long courseId) {
	    Course course = this.courseservice.getCourse(courseId);

	    if (course != null) {
	        return new ResponseEntity<>(course, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/courses")
	public ResponseEntity<Course> addCourse(@RequestBody Course newCourse) {
	    Course addedCourse = this.courseservice.addCourse(newCourse);

	    if (addedCourse != null) {
	        return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);
	    } else {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	@PutMapping("/courses/{courseId}")
	public ResponseEntity<Course> updateCourse(@PathVariable long courseId, @RequestBody Course updatedCourse) {
	    Course existingCourse = this.courseservice.getCourse(courseId);

	    if (existingCourse != null) {
	        existingCourse.setTitle(updatedCourse.getTitle());
	        existingCourse.setDescription(updatedCourse.getDescription());

	        Course updated = this.courseservice.updateCourse(existingCourse);
	        return new ResponseEntity<>(updated, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<HttpStatus>deleteCourse(@PathVariable String courseId){
		try {
			this.courseservice.deleteCourse(Long.parseLong(courseId));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
