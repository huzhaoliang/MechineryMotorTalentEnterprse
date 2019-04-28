package com.mmt.enterprise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mmt.enterprise.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
	@Query(value="select a.* from job a where a.com_id=:companyId", nativeQuery = true)
	List<Job> findJobsByCompanyId(@Param("companyId") Long companyId);
	
	@Modifying
	@Transactional
	@Query(value="delete from job where id=:id", nativeQuery = true)
	void deleteJobById(@Param("id") Long id);
}
