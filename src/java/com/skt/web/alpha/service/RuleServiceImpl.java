package com.skt.web.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.web.alpha.dao.RuleDao;
import com.skt.web.alpha.model.Rule;
import com.skt.web.common.exception.ApplicationException;

@Service
public class RuleServiceImpl implements RuleService {

	@Autowired
	RuleDao ruleDao;

	@Override
	public List<Rule> getRules() throws ApplicationException {
		return ruleDao.findAll(Rule.class);
	}
}
