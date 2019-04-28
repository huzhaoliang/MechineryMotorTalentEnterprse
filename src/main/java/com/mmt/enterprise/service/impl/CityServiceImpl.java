package com.mmt.enterprise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmt.enterprise.entity.City;
import com.mmt.enterprise.repository.CityRepository;
import com.mmt.enterprise.service.CityService;

@Service("CityService")
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<City> getProvinces() {
		return cityRepository.findProvinces();
	}

	@Override
	public List<City> getCitiesByParentId(Long parentId) {
		return cityRepository.getCitiesByParentId(parentId);
	}

}
