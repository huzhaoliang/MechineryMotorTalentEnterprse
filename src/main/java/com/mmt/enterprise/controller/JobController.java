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
        Page<Job> jobs = jobService.getJobs(user.getId(), name, pageNumber, pageSize);
        jobs.getTotalPages();
        if(jobs != null) {
            model.addAttribute("jobs", jobs);
        }
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("name", name);
        return "enterprise/job_list";
    }

    @RequestMapping(value="/enterprise/job_add")
    public String add(Model model) {
        logger.info("++++++++job add++++++++++");
        List<City> provinces = cityService.getCityByFlag(1l);
        if(provinces != null) {
            model.addAttribute("provinces", provinces);
        }
        List<City> cities = cityService.getCityByFlag(2l);
        if(cities != null){
            model.addAttribute("cities", cities);
        }
        List<JobType> topTypes = jobTypeService.getTypesByFlag(1l);
        if(topTypes != null){
            model.addAttribute("topTypes", topTypes);
        }
        List<JobType> subTypes = jobTypeService.getTypesByFlag(2l);
        if(subTypes != null){
            model.addAttribute("subTypes", subTypes);
        }
        return "enterprise/job_add";
    }

    @RequestMapping(value="/enterprise/job_view")
    public String view(Model model, @ModelAttribute(value="id") Long id) {
        logger.info("++++++++job view++++++++++");
        Job job = jobService.getJobById(id);
        if(job != null){
            model.addAttribute("job", job);
        }
        return "enterprise/job_view";
    }

    @RequestMapping(value="/enterprise/job_save", method=RequestMethod.POST)
    public String save(@ModelAttribute(value="jobForm") Job job) {
        logger.info("++++++++job save++++++++++");
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
        List<City> provinces = cityService.getCityByFlag(1l);
        if(provinces != null) {
            model.addAttribute("provinces", provinces);
        }
        List<City> cities = cityService.getCityByFlag(2l);
        if(cities != null){
            model.addAttribute("cities", cities);
        }
        List<JobType> topTypes = jobTypeService.getTypesByFlag(1l);
        if(topTypes != null){
            model.addAttribute("topTypes", topTypes);
        }
        List<JobType> subTypes = jobTypeService.getTypesByFlag(2l);
        if(subTypes != null){
            model.addAttribute("subTypes", subTypes);
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
