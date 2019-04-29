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
	public JobType saveJobType(JobType jobType) {
		return jobTypeRepository.saveAndFlush(jobType);
	}

	@Override
	public Page<JobType> getJobTypes(int pageNumber, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
		Page<JobType> jobTypes = jobTypeRepository.findAll(request);
		return jobTypes;
	}

	@Override
	public void deleteJobType(List<JobType> jobTypes) {
		jobTypeRepository.deleteInBatch(jobTypes);
	}

}
