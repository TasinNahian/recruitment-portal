package com.applicantportal.ApplicantPortal.controller.jobs;

import com.applicantportal.ApplicantPortal.dto.Jobsdto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public interface    JobsController {
    @GetMapping("/jobs/all")
    public String showAllJobs(Model model);
    @GetMapping("/alljobs/page={pageNo}")
    public String showJobsOnePage(@PathVariable("pageNo") int pageNo,
                              @RequestParam("sortField") String sortField,
                              @RequestParam("sortDir") String sortDir,
                              @RequestParam("dropdownSize") int size,
                              Model model);
    @GetMapping("/addjob")
    public ModelAndView addJobs(HttpSession session, HttpServletRequest request, HttpServletResponse response);
    @PostMapping("/savejob")
    public String saveJob(@ModelAttribute Jobsdto jobsdto) throws IOException;
    @GetMapping("/showupdateform")
    public ModelAndView showUpdateForm(@RequestParam int id);
    @GetMapping("/delete")
    public String deleteJob(@RequestParam int id);
    @GetMapping("/showDetails")
    public String showDetails(@RequestParam int id, Model model);
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, @RequestParam("columnName") String columnName, Model model);

    @GetMapping("/alljobs/pagequery")
    String getPageSize(@RequestParam("size") int size, Model model);
    @GetMapping("/home")
    ModelAndView home(ModelAndView model);
}
