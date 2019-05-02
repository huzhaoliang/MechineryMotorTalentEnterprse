package com.mmt.enterprise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmt.enterprise.entity.City;

public interface CityRepository extends JpaSpecificationExecutor<City>, JpaRepository<City, Long> {
	@Query(value="select a.* from city a where a.flag=:flag", nativeQuery = true)
	List<City> getTypesByFlag(@Param("flag") int flag);

	@Query(value="select * from city order by name", nativeQuery = true)
	List<City> getAllCities();

	@Query(value="select a.* from city a where a.parent_id=:parentId order by a.name", nativeQuery = true)
	List<City> getSubCityByParentId(@Param("parentId") Long parentId);
}
