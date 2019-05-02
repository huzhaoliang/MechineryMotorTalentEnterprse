package com.mmt.enterprise.service;

import java.util.List;
import com.mmt.enterprise.entity.JobType;

public interface JobTypeService {
	List<JobType> getTypesByFlag(int flag);

	List<JobType> getSubType(Long parentId);
}
