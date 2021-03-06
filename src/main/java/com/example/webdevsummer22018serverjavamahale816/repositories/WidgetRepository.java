package com.example.webdevsummer22018serverjavamahale816.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer22018serverjavamahale816.models.Topic;
import com.example.webdevsummer22018serverjavamahale816.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer>{
	
	@Transactional
	
	@Query("SELECT w from Widget w WHERE w.topic =:topic ORDER BY w.id")
	List<Widget> findAllWidgetsByTopicSorted(@Param("topic") Topic topic);
	
	@Modifying
	@Query("DELETE FROM Widget w WHERE w.topic.id=:topicId")
	void deleteWidgetsByTopicId(@Param("topicId") int topicId);
}
