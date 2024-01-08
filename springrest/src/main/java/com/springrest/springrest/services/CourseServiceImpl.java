package com.springrest.springrest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entities.Course;

@Service
public class CourseServiceImpl implements CourseService {

//	List<Course> list;
	@Autowired
	private CourseDao coursedao;
	
	public CourseServiceImpl() {
//		list= new ArrayList<>();
//		list.add(new Course(145,"Java Core Course","this course contains basic of java"));
//		list.add(new Course(4343,"Spring Boot course","Creating Rest API using Spring boot"));
		
	}

	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
//		return list;
		return coursedao.findAll();
	}

	@Override
	public Course getCourse(long courseId) {
	    return coursedao.findById(courseId).orElse(null);
	}


	@Override
	public Course addCourse(Course course) {
	    try {
	        return coursedao.save(course);
	    } catch (Exception e) {
	        // Handle any exceptions that may occur during course addition
	        e.printStackTrace(); // Log the exception for debugging purposes
	        return null; // Return null to indicate failure
	    }
	}



	@Override
	public Course updateCourse(Course course) {
	    // Make sure the course with the provided ID exists before updating
	    if (coursedao.existsById(course.getId())) {
	        return coursedao.save(course);
	    } else {
	        // Handle the case where the course with the given ID doesn't exist
	        throw new RuntimeException("Course not found with id: " + course.getId());
	    }
	}


	@Override
	public void deleteCourse(long courseId) {
//		Course deletedCourse=null;
//		for(Course course :list) {
//			if(course.getId()==courseId) {
//				deletedCourse = course;
//				list.remove(course);
//			}
//		}
//		return deletedCourse;
		Course entity=coursedao.getReferenceById(courseId);
		coursedao.delete(entity);
		
	}

}
