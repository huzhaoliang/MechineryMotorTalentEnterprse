package com.mmt.enterprise.service;

import com.mmt.enterprise.entity.EnterpriseUser;

public interface EnterpriseService {

	public EnterpriseUser getEnterpriseUserByEmail(String email);

	public EnterpriseUser getEnterpriseUserByEmailAndPwd(String email, String password);
}
