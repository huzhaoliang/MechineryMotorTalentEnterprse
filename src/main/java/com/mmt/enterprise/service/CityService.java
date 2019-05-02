package com.mmt.enterprise.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mmt.enterprise.entity.City;

public interface CityService {
	
	List<City> getCityByFlag(Long flag);

	City getCityById(Long id);

	List<City> getAllCities();
}
