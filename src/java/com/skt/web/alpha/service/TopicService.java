package com.skt.web.alpha.service;

import java.util.List;

import com.skt.web.alpha.model.Topic;
import com.skt.web.common.exception.ApplicationException;

public interface TopicService {

	Topic createTopic(Topic topic) throws ApplicationException;

	Topic getTopic(int topicId) throws ApplicationException;

	Topic updateTopic(Topic topic) throws ApplicationException;

	List<Object[]> findAllTopicTosByGroupIds(List<Integer> groupIds)
			throws ApplicationException;

	List<Object[]> findAllTopicTosByOwnerUserId(int ownerUserId)
			throws ApplicationException;
	
	List<String> findAllTopicListByGroupCategory(String groupCategory);
	
	// Get List of topic by Group Id.
	List<Integer> findAllTopicListByGroupId(int groupId) throws ApplicationException;
}
