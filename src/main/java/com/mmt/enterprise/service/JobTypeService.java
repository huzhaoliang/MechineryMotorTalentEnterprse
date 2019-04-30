package com.mmt.enterprise.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mmt.enterprise.entity.JobType;

public interface JobTypeService {
	JobType saveJobType(JobType jobType);
	
	Page<JobType> getJobTypes(int pageNumber, int pageSize);
	
	void deleteJobTypeById(Long id);

	JobType getJobTypeById(Long id);
}
