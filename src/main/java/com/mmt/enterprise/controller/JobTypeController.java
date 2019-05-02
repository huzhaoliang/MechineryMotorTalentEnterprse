package com.mmt.enterprise.controller;

import com.mmt.enterprise.entity.City;
import com.mmt.enterprise.entity.JobType;
import com.mmt.enterprise.service.CityService;
import com.mmt.enterprise.service.JobTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobTypeController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JobTypeService jobTypeService;

    @ResponseBody
    @RequestMapping(value="/enterprise/sub_type")
    public String getSubType(@ModelAttribute(value="parentId") String parentId){
        StringBuilder sb = new StringBuilder();
        sb.append("<option value=\"\">---子类别---</option>");
        List<JobType> list = jobTypeService.getSubType(Long.valueOf(parentId));
        for(JobType type : list){
            sb.append("<option value='").append(type.getId()).append("'>").append(type.getType()).append("</option>");
        }
        return sb.toString();
    }
}
