package com.mmt.enterprise.controller;

import com.mmt.enterprise.entity.City;
import com.mmt.enterprise.entity.EnterpriseUser;
import com.mmt.enterprise.entity.Job;
import com.mmt.enterprise.entity.JobType;
import com.mmt.enterprise.service.CityService;
import com.mmt.enterprise.service.EnterpriseService;
import com.mmt.enterprise.service.JobService;
import com.mmt.enterprise.service.JobTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
public class JobController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static int pageSize = 20;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private JobService jobService;

    @Autowired
    private CityService cityService;

    @Autowired
    private JobTypeService jobTypeService;

    @RequestMapping(value="/enterprise/job_list")
    public String list(Model model, @ModelAttribute(value="name") String name,
                       @ModelAttribute(value="cityId") String cityId,
                       @ModelAttribute(value="pageNumber") int pageNumber) {
        System.out.println("++++++++job list++++++++++" + name);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        logger.info("username is "+ username);
        EnterpriseUser user = enterpriseService.getEnterpriseUserByName(username);

        pageNumber = pageNumber < 1?1:pageNumber;
        City city = null;
        if(!"".equals(cityId)){
            city = cityService.getCityById(Long.valueOf(cityId));
        }
        Page<Job> jobs = jobService.getJobs(user.getId(),city, name, pageNumber, pageSize);
        if(jobs != null) {
            model.addAttribute("jobs", jobs);
        }
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        model.addAttribute("cityId", cityId);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", jobs.getTotalPages());
        model.addAttribute("name", name);
        return "enterprise/job_list";
    }

    @RequestMapping(value="/enterprise/job_add")
    public String add(Model model) {
        logger.info("++++++++job add++++++++++");
        List<City> provinces = cityService.getCityByFlag(1);
        if(provinces != null) {
            model.addAttribute("provinces", provinces);
        }
        List<JobType> topTypes = jobTypeService.getTypesByFlag(1);
        if(topTypes != null){
            model.addAttribute("topTypes", topTypes);
        }
        return "enterprise/job_add";
    }

    @RequestMapping(value="/enterprise/job_save", method=RequestMethod.POST)
    public String save(@ModelAttribute(value="jobForm") Job job) {
        logger.info("++++++++job save++++++++++");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        logger.info("username is "+ username);
        EnterpriseUser user = enterpriseService.getEnterpriseUserByName(username);
        job.setPublishTime(new Date());
        job.setComId(user.getId());
        jobService.insertJob(job);
        return "redirect:job_list";
    }

    @RequestMapping(value="/enterprise/job_update")
    public String update(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++job update++++++++++");
        Job job = jobService.getJobById(id);
        if(job != null) {
            model.addAttribute("job", job);
        }
        List<City> provinces = cityService.getCityByFlag(1);
        if(provinces != null) {
            model.addAttribute("provinces", provinces);
        }
        List<JobType> topTypes = jobTypeService.getTypesByFlag(1);
        if(topTypes != null){
            model.addAttribute("topTypes", topTypes);
        }
        if(job.getCity().getParentId() != null){
            model.addAttribute("provId", job.getCity().getParentId());
            model.addAttribute("cityId", job.getCity().getId());
            List<City> cities = cityService.getSubCityByParentId(job.getCity().getParentId());
            model.addAttribute("cities", cities);
        }else{
            model.addAttribute("provId", job.getCity().getId());
            List<City> cities = cityService.getSubCityByParentId(job.getCity().getId());
            model.addAttribute("cities", cities);
        }

        if(job.getJobType().getParentId() != null){
            model.addAttribute("topTypeId", job.getJobType().getParentId());
            model.addAttribute("subTypeId", job.getJobType().getId());
            List<JobType> types = jobTypeService.getSubType(job.getJobType().getParentId());
            model.addAttribute("subTypes", types);
        }else{
            model.addAttribute("topTypeId", job.getJobType().getId());
            List<JobType> types = jobTypeService.getSubType(job.getJobType().getId());
            model.addAttribute("subTypes", types);
        }
        return "enterprise/job_update";
    }

    @RequestMapping(value="/enterprise/job_delete")
    public String delete(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++job delete++++++++++"+id);
        jobService.deleteJobById(id);
        return "redirect:job_list";
    }
}
