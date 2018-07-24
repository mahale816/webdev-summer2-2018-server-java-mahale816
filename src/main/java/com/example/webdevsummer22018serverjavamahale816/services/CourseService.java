package com.example.webdevsummer22018serverjavamahale816.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer22018serverjavamahale816.models.Course;
import com.example.webdevsummer22018serverjavamahale816.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*")
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public List<Course> findAllCourses() {
		return (List<Course>) courseRepository.findAll();
	}

	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}
	
	@GetMapping("/api/course/{courseId}")
	public Optional<Course> findCourseById(@PathVariable("courseId") int id){
		return courseRepository.findById(id);
	}
	
	@PutMapping("/api/course/{courseId}")
	public Course updateCourse(@PathVariable("courseId")int id,@RequestBody Course course)
	{
		Optional<Course> courseData = courseRepository.findById(id);
		if(courseData.isPresent())
		{
			Course crs = courseData.get();
			crs.setTitle(course.getTitle());
			//crs.setCreated(course.getCreated());
			crs.setModified(course.getModified());
			courseRepository.save(crs);
			return courseRepository.findById(id).get();
		}
		return null;
		
	}

}
