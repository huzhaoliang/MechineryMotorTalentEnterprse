package com.mmt.enterprise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmt.enterprise.entity.City;

public interface CityRepository extends JpaSpecificationExecutor<City>, JpaRepository<City, Long> {
	@Query(value="select a.* from city a where a.flag=1", nativeQuery = true)
	List<City> findProvinces();

	@Query(value="select a.* from city a where a.parent_id=:parentId", nativeQuery = true)
	List<City> getCitiesByParentId(@Param("parentId") Long parentId);
}
