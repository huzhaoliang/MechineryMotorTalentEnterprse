package com.mmt.enterprise.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mmt.service.SignUpService;

@Service(value="SignUpService")
public class SignUpServiceImpl implements SignUpService
{
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean checkIfUserExisted(String _username) 
	{
		logger.info("+++ check if the user is existed +++");
		return false;
	}

	@Override
	public boolean doUserSignUp(String _username, String _password) 
	{
		logger.info("+++ to regisiter user +++");
		return false;
	}

}
