package com.skt.web.alpha.dao;

import java.util.List;

import com.skt.web.alpha.model.Topic;
import com.skt.web.common.dao.BaseDao;

public interface TopicDao extends BaseDao<Topic> {

	List<Object[]> findAllTopicTosByGroupIds(List<Integer> groupIds);

	List<Object[]> findAllTopicTosByOwnerUserId(int ownerUserId);

	List<String> findAllTopicListByGroupCategory(String groupCategory);

	List<Integer> findAllTopicListByGroupId(int groupId);
}
