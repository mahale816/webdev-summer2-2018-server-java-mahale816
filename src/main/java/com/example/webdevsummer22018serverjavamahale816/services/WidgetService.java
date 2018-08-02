package com.example.webdevsummer22018serverjavamahale816.services;

import java.util.ArrayList;
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

import com.example.webdevsummer22018serverjavamahale816.models.Topic;
import com.example.webdevsummer22018serverjavamahale816.models.Widget;
import com.example.webdevsummer22018serverjavamahale816.repositories.TopicRepository;
import com.example.webdevsummer22018serverjavamahale816.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@PostMapping("/api/topic/{topicId}/widget")
	public Widget createWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody Widget newWidget) {
		Optional<Topic> data = topicRepository.findById(topicId);
		
		if(data.isPresent()) {
			Topic topic = data.get();
			newWidget.setTopic(topic);
			return widgetRepository.save(newWidget);

		}
		return null;		
	}
	
	@PostMapping("/api/topic/{topicId}/widgets")
	public List<Widget> createWidgets(
			@PathVariable("topicId") int topicId,
			@RequestBody List<Widget> newWidgets) {
		widgetRepository.deleteWidgetsByTopicId(topicId);
		List<Widget> output = new ArrayList<Widget>();
	for(Widget wid : newWidgets) {
		output.add(createWidget(topicId,wid));
	}
		return output;
	}
	
	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findAllWidgetsForTopic(
			@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic top = data.get();
			return widgetRepository.findAllWidgetsByTopicSorted(top);
		}
		return null;		
	}
	
	@PostMapping("/api/widget/save/{topicId}")
	public List<Widget> saveAllWidgets(@RequestBody List<Widget> widgets, @PathVariable("topicId") int topicId) {
		Optional<Topic> topicData = topicRepository.findById(topicId);
		List<Widget> response = new ArrayList<Widget>();
		if (topicData.isPresent()) {
			List<Widget> widList = topicData.get().getWidgets();
			widgetRepository.deleteAll(widList);
			for (Widget wid : widgets) {
				wid.setTopic(topicData.get());
				response.add(widgetRepository.save(wid));
			}
			return response;
		} 
		else 
		{
			return new ArrayList<Widget>();
		}
	}
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	
	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(
			@PathVariable("widgetId") int widgetId,
			@RequestBody Widget newWidget) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			Widget widget = data.get();
			widget.setName(newWidget.getName());
			widget.setWidgetOrder(newWidget.getWidgetOrder());
			widget.setText(newWidget.getText());
			widget.setSize(newWidget.getSize());
			widget.setListType(newWidget.getListType());
			widget.setWidgetType(newWidget.getWidgetType());
			widget.setLinkName(newWidget.getLinkName());
			widgetRepository.save(widget);
			return widget;
		}
		return null;		
	}

	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId)
	{	
		widgetRepository.deleteById(widgetId);
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets(){
		
		return (List<Widget>) widgetRepository.findAll();
		
	}
}
