package com.skt.web.alpha.util;

import org.springframework.stereotype.Component;

import com.skt.web.alpha.model.Topic;
import com.skt.web.alpha.to.TopicTo;

@Component
public class TopicUtils {

	public void setTopicFromTopicTo(Topic topic, TopicTo topicTo) {
		topic.setDescription(topicTo.getDescription());
		topic.setId(topicTo.getId());
		topic.setImagePath(topicTo.getImagePath());
		topic.setLastViewTime(topicTo.getLastViewTime());
		topic.setName(topicTo.getName());
		topic.setTopicLikeCount(topicTo.getTopicLikeCount());
		topic.setTopicSpamCount(topicTo.getTopicSpamCount());
		// Always set topicStatus as OPEN while creating topic
		topic.setTopicStatus(topicTo.getTopicStatus());
		topic.setTopicViewCount(topicTo.getTopicViewCount());
		topic.setType(topicTo.getType());
		topic.setPhotoId(topicTo.getPhotoId());
	}

	public void setTopicToFromTopic(TopicTo topicTo, Topic topic) {
		topicTo.setCreationTime(topic.getCreationTime());
		topicTo.setDescription(topic.getDescription());
		topicTo.setGroupDisplayName(topic.getGroupDisplayName());
		topicTo.setGroupId(topic.getGroup().getId());
		topicTo.setId(topic.getId());
		topicTo.setImagePath(topic.getImagePath());
		topicTo.setLastViewTime(topic.getLastViewTime());
		topicTo.setName(topic.getName());
		topicTo.setOwnerUserId(topic.getOwnerUser().getId());
		topicTo.setOwnerUserName(topic.getOwnerUserName());
		topicTo.setTopicLikeCount(topic.getTopicLikeCount());
		topicTo.setTopicSpamCount(topic.getTopicSpamCount());
		topicTo.setTopicStatus(topic.getTopicStatus());
		topicTo.setTopicViewCount(topic.getTopicViewCount());
		topicTo.setType(topic.getType());
		topicTo.setPhotoId(topic.getPhotoId());
	}
}
