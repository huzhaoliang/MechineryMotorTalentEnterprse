package com.mmt.enterprise.controller;

import com.mmt.enterprise.entity.City;
import com.mmt.enterprise.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CityService cityService;

    @ResponseBody
    @RequestMapping(value="/enterprise/sub_city")
    public String getSubCity(@ModelAttribute(value="parentId") String parentId){
        StringBuilder sb = new StringBuilder();
        sb.append("<option value=\"\">---选择城市---</option>");
        List<City> list = cityService.getSubCityByParentId(Long.valueOf(parentId));
        for(City city : list){
            sb.append("<option value='").append(city.getId()).append("'>").append(city.getName()).append("</option>");
        }
        return sb.toString();
    }
}
