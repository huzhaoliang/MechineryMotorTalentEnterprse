package com.mmt.enterprise.service;

import com.mmt.enterprise.entity.EnterpriseUser;

public interface EnterpriseService {

	public EnterpriseUser getEnterpriseUserByName(String name);

	public EnterpriseUser getEnterpriseUserByNameAndPwd(String name, String password);
}
