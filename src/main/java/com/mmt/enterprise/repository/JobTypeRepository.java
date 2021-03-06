package com.mmt.enterprise.repository;

import com.mmt.enterprise.entity.City;
import com.mmt.enterprise.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mmt.enterprise.entity.JobType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobTypeRepository extends JpaRepository<JobType, Long>{
    @Query(value="select a.* from job_type a where a.flag=:flag", nativeQuery = true)
    List<JobType> getTypesByFlag(@Param("flag") int flag);

    @Query(value="select a.* from job_type a where a.parent_id=:parentId order by a.type", nativeQuery = true)
    List<JobType> getSubTypeByParentId(@Param("parentId") Long parentId);
}
