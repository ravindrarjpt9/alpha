package com.skt.web.alpha.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skt.web.alpha.service.RuleService;
import com.skt.web.alpha.to.ErrorResponse;
import com.skt.web.alpha.to.Response;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class RuleController {
	private static final Logger LOG = Logger.getLogger(RuleController.class);

	@Autowired
	RuleService ruleService;

	@Transactional
	@RequestMapping(value = "/getRules", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getRules() {
		boolean success = false;
		Object data = null;
		try {
			data = ruleService.getRules();
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}
}
