package com.skt.web.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.web.alpha.dao.TopicDao;
import com.skt.web.alpha.model.Topic;
import com.skt.web.common.exception.ApplicationException;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicDao topicDao;

	@Override
	public Topic createTopic(Topic topic) throws ApplicationException {
		// Creating the topic in DB
		return topicDao.persist(topic);
	}

	@Override
	public Topic getTopic(int topicId) throws ApplicationException {
		Topic topic = topicDao.findById(Topic.class, topicId);
		if (topic == null) {
			throw new ApplicationException("No topic exists for the TOPIC_ID: "
					+ topicId);
		} else {
			return topic;
		}
	}

	@Override
	public Topic updateTopic(Topic topic) throws ApplicationException {
		return topicDao.merge(topic);
	}

	@Override
	public List<Object[]> findAllTopicTosByGroupIds(List<Integer> groupIds)
			throws ApplicationException {

		List<Object[]> topicTos = topicDao.findAllTopicTosByGroupIds(groupIds);

		if (topicTos.isEmpty()) {
			throw new ApplicationException(
					"No topics exists for the specified List of GROUP_IDs");
		} else {
			return topicTos;
		}
	}

	@Override
	public List<Object[]> findAllTopicTosByOwnerUserId(int ownerUserId)
			throws ApplicationException {

		List<Object[]> topicTos = topicDao
				.findAllTopicTosByOwnerUserId(ownerUserId);

		if (topicTos.isEmpty()) {
			throw new ApplicationException(
					"No topics exists for the OWNER_USER_ID: " + ownerUserId);
		} else {
			return topicTos;
		}
	}
	
	@Override
	public List<String> findAllTopicListByGroupCategory(String groupCategory) {
		
		List<String> topicList = topicDao
				.findAllTopicListByGroupCategory(groupCategory);

		if (topicList.isEmpty()) {
			 topicList = topicDao
					.findAllTopicListByGroupCategory("Other");
		} 
		return topicList;
	}
	@Override
	public List<Integer> findAllTopicListByGroupId(int groupId)
			throws ApplicationException {
		List<Integer> topicList = topicDao
				.findAllTopicListByGroupId(groupId);
		return topicList;
	}
}
