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
import com.example.webdevsummer22018serverjavamahale816.models.Lesson;
import com.example.webdevsummer22018serverjavamahale816.models.Module;
import com.example.webdevsummer22018serverjavamahale816.repositories.CourseRepository;
import com.example.webdevsummer22018serverjavamahale816.repositories.LessonRepository;
import com.example.webdevsummer22018serverjavamahale816.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*")
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons() {
		return (List<Lesson>) lessonRepository.findAll();
	}
	
	@PostMapping("/api/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(
			@RequestBody Lesson lesson, @PathVariable("cid") int cid, @PathVariable("mid") int mid)
	{	
		Optional<Course> courseData = courseRepository.findById(cid);
		if(courseData.isPresent())
		{
			Optional<Module> moduleData = moduleRepository.findById(mid);
			if(moduleData.isPresent())
			{
				Module mod = moduleData.get();
				lesson.setModule(mod);
				return lessonRepository.save(lesson);
			}
		}
		return null;
		
	}
	
	@GetMapping("/api/course/{cid}/module/{mid}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("cid") int cid,@PathVariable("mid") int mid)
	{
		Optional<Course> courseData = courseRepository.findById(cid);
		if(courseData.isPresent())
		{
			Optional<Module> moduleData = moduleRepository.findById(mid);
			if(moduleData.isPresent())
			{
				Module m = moduleData.get();
				return m.getLessons();
			}
		}
		return null;	
	}
	
	@DeleteMapping("/api/lesson/{id}")
	public void deleteLesson(@PathVariable("id")int id)
	{
		lessonRepository.deleteById(id);
	}

	
	@GetMapping("/api/lesson/{id}")
	public Lesson findLessonById(@PathVariable("id")int id) {
		return lessonRepository.findById(id).get();
		
	}
	
	@PutMapping("/api/lesson/{id}")
	public Lesson updateLesson(@PathVariable("id")int id, @RequestBody Lesson lesson)
	{
		Optional<Lesson> less = lessonRepository.findById(id);
		if (less.isPresent())
		{
			Lesson ls = less.get();
			ls.setTitle(lesson.getTitle());
			lessonRepository.save(ls);
			return lessonRepository.findById(id).get();
		}
		return null;
		
	}

}
