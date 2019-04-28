package com.mmt.enterprise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mmt.enterprise.entity.Job;
import com.mmt.enterprise.repository.JobRepository;
import com.mmt.enterprise.service.JobService;

@Service("JobService")
public class JobServiceImpl implements JobService{

	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public Job insertJob(Job job) {
		return jobRepository.saveAndFlush(job);
	}

	@Override
	public Page<Job> getJobs(int pageNumber, int pageSize) {
		PageRequest request = PageRequest.of(pageNumber - 1, pageSize, null);
		Page<Job> jobs = jobRepository.findAll(request);
		return jobs;
	}

	@Override
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}

	@Override
	public List<Job> getJobsByCompany(Long companyId) {
		return jobRepository.findJobsByCompanyId(companyId);
	}

	@Override
	public void deleteJobs(List<Job> jobs) {
		jobRepository.deleteInBatch(jobs);
	}

	@Override
	public void deleteJobById(Long id) {
		jobRepository.deleteJobById(id);
	}

}
