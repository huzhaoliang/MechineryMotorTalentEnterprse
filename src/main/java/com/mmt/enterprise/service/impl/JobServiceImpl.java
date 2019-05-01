package com.mmt.enterprise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mmt.enterprise.entity.Job;
import com.mmt.enterprise.repository.JobRepository;
import com.mmt.enterprise.service.JobService;

import javax.persistence.criteria.*;

@Service("JobService")
public class JobServiceImpl implements JobService{

	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public Job insertJob(Job job) {
		return jobRepository.saveAndFlush(job);
	}

	@Override
	public Page<Job> getJobs(Long comId, String name, int pageNumber, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
		PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
		Specification<Job> spec = new Specification<Job>() {
			public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p = null;
				Path<String> comAttribute = root.get("company.id");
				Predicate p1 = cb.equal(comAttribute, comId);
				if(!"".equals(name)){
					Path<String> nameAttribute = root.get("name");
					Predicate p2 = cb.like(nameAttribute, "%"+name+"%");
					p = cb.and(p1, p2);
				}else{
					p = cb.and(p1);
				}
				return p;
			}
		};
		Page<Job> jobs = jobRepository.findAll(spec, request);
		return jobs;
	}

	@Override
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}

	@Override
	public void deleteJobById(Long id) {
		jobRepository.deleteById(id);
	}

	@Override
	public List<Job> getJobsByType(Long typeId) {
		return jobRepository.findJobsByJobTypeId(typeId);
	}

	@Override
	public Job getJobById(Long id) {
		return jobRepository.getOne(id);
	}

}
