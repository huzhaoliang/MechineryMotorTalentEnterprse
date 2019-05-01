package com.mmt.enterprise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmt.enterprise.entity.Job;

public interface JobRepository extends JpaSpecificationExecutor<Job>, JpaRepository<Job, Long> {
	@Query(value="select a.* from job a where a.com_id=:companyId", nativeQuery = true)
	List<Job> findJobsByCompanyId(@Param("companyId") Long companyId);

	@Query(value="select a.* from job a where a.type_id=:typeId", nativeQuery = true)
	List<Job> findJobsByJobTypeId(@Param("typeId") Long typeId);
}
