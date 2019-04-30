package com.mmt.enterprise.service.impl;

import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mmt.enterprise.entity.EnterpriseUser;
import com.mmt.enterprise.repository.EnterpriseRepository;
import com.mmt.enterprise.service.EnterpriseService;

@Service("EnterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService{

	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Override
	public EnterpriseUser getEnterpriseUserByEmail(String email){
		return enterpriseRepository.getEnterpriseByEmail(email);
	}

	@Override
	public EnterpriseUser getEnterpriseUserByEmailAndPwd(String email, String password) {
		return enterpriseRepository.getEnterpriseByEmailAndPwd(email, password);
	}

	@Override
	public EnterpriseUser saveEnterprise(EnterpriseUser user) {
		return enterpriseRepository.saveAndFlush(user);
	}

	@Override
	public EnterpriseUser getEnterpriseUserByName(String name) {
		return enterpriseRepository.getEnterpriseByName(name);
	}

}
