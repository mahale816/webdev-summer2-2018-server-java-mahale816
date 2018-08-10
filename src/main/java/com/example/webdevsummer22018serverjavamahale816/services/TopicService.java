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
import com.example.webdevsummer22018serverjavamahale816.models.Topic;
import com.example.webdevsummer22018serverjavamahale816.repositories.CourseRepository;
import com.example.webdevsummer22018serverjavamahale816.repositories.LessonRepository;
import com.example.webdevsummer22018serverjavamahale816.repositories.ModuleRepository;
import com.example.webdevsummer22018serverjavamahale816.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*")
public class TopicService {
	@Autowired
	TopicRepository topicRepository;

	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/topic")
	public List<Topic> findAllTopics() {
		return (List<Topic>) topicRepository.findAll();
	}
	
	@PostMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
	public Topic createTopic(
			@RequestBody Topic topic, @PathVariable("cid") int cid, @PathVariable("mid") int mid, 
			@PathVariable("lid") int lid)
	{	
		Optional<Course> courseData = courseRepository.findById(cid);
		if(courseData.isPresent())
		{
			Optional<Module> moduleData = moduleRepository.findById(mid);
			if(moduleData.isPresent())
			{
				Optional<Lesson> lessonData = lessonRepository.findById(lid);
				if(lessonData.isPresent())
				{
					Lesson les = lessonData.get();
					topic.setLesson(les);
					return topicRepository.save(topic);
				}
				
			}
		}
		return null;
		
	}
	
	@GetMapping("/api/lesson/{lid}/topic")
	public List<Topic> findAllTopicForLesson(
			@PathVariable("lid") int lid)
	{
				Optional<Lesson> lessonData = lessonRepository.findById(lid);
				if(lessonData.isPresent())
				{
					Lesson les = lessonData.get();
					return les.getTopics();
				}
			
		return null;	
	}
	
	@PutMapping("/api/topic/{id}")
	public Topic updateTopic(
			@PathVariable("id")int id,
			@RequestBody Topic topic)
	{
		Optional<Topic> tpc = topicRepository.findById(id);
		if (tpc.isPresent())
		{
			Topic t = tpc.get();
			t.setTitle(topic.getTitle());
			topicRepository.save(t);
			return topicRepository.findById(id).get();
		}
		return null;
		
	}
	
	@DeleteMapping("/api/topic/{id}")
	public void deleteTopic(@PathVariable("id")int id)
	{
		topicRepository.deleteById(id);
	}

	
	@GetMapping("/api/topic/{id}")
	public Topic findTopicById(@PathVariable("id")int id) {
		return topicRepository.findById(id).get();
		
	}
}
