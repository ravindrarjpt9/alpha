package com.skt.web.alpha.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skt.web.alpha.service.GroupUserService;
import com.skt.web.alpha.to.GroupCategoryTo;
import com.skt.web.alpha.to.GroupRulesTo;
import com.skt.web.alpha.to.Response;

@Controller
public class GroupsRuleController {

	private static final Logger LOG = Logger.getLogger(GroupsRuleController.class);

	@Autowired
	GroupUserService groupUserService;
	
	@Transactional
	@RequestMapping(value = "/getRulesListByGroupeCategory", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getTopicTosByOwnerUserId(@RequestBody GroupRulesTo groupRulesTo) {
		boolean success = false;
		Object data = null;

		LOG.info("Getting list of group rules from ["+groupRulesTo.toString()+"]");
		String groupCategoryName = groupRulesTo.getGroupCategory();
		// Fetching the required data from DB as a List of Object[]
		List<String> rulesListToObjectArrays = groupUserService
				.findAllGroupRulesListByGroupCategory(groupCategoryName);

		// Creating instance of topicTos to be returned
//		List<String> topicListTos = new ArrayList<>();
//		for (Object[] topicToObjectArray : topicListToObjectArrays) {
//			System.out.println(topicToObjectArray[0]);
//			
//			topicListTos.add(topicToObjectArray[0].toString());
//		}
		// Returning the List of topicTos
		groupRulesTo.setRulesList(rulesListToObjectArrays);
		data = groupRulesTo;
		success = true;
		return new Response(success, data);
	}

}
