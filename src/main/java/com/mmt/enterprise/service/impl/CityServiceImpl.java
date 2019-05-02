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
	public List<City> getCityByFlag(int flag) {
		return cityRepository.getTypesByFlag(flag);
	}

	@Override
	public City getCityById(Long id) {
		return cityRepository.getOne(id);
	}

	@Override
	public List<City> getAllCities() {
		return cityRepository.getAllCities();
	}

}
