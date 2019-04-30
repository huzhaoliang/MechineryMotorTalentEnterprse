package com.mmt.enterprise.service;

import com.mmt.enterprise.entity.EnterpriseUser;

public interface EnterpriseService {

	EnterpriseUser getEnterpriseUserByEmail(String email);

	EnterpriseUser getEnterpriseUserByEmailAndPwd(String email, String password);

	EnterpriseUser saveEnterprise(EnterpriseUser user);

	EnterpriseUser getEnterpriseUserByName(String name);
}
