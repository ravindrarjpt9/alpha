package com.skt.web.alpha.service;

import java.util.List;

import com.skt.web.alpha.model.Rule;
import com.skt.web.common.exception.ApplicationException;

public interface RuleService {

	List<Rule> getRules() throws ApplicationException;
}
