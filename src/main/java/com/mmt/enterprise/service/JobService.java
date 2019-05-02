package com.mmt.enterprise.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mmt.enterprise.entity.Job;

public interface JobService {
	Job insertJob(Job job);
	
	Page<Job> getJobs(Long comId, Long cityId, String name, int pageNumber, int pageSize);
	
	List<Job> getAllJobs();
	
	void deleteJobById(Long id);

	List<Job> getJobsByType(Long typeId);

	Job getJobById(Long id);
}
