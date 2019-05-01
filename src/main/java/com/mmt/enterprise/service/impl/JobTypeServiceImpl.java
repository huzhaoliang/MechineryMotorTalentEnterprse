package com.mmt.enterprise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.mmt.enterprise.entity.JobType;
import com.mmt.enterprise.repository.JobTypeRepository;
import com.mmt.enterprise.service.JobTypeService;

public class JobTypeServiceImpl implements JobTypeService{

	@Autowired
	private JobTypeRepository jobTypeRepository;

	@Override
	public List<JobType> getTypesByFlag(Long flag) {
		return jobTypeRepository.getTypesByFlag(flag);
	}
}
