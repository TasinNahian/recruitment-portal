package com.applicantportal.ApplicantPortal.service.jobs;

import com.applicantportal.ApplicantPortal.dto.Jobsdto;
import com.applicantportal.ApplicantPortal.repository.jobs.JobsDemoRepo;
import com.applicantportal.ApplicantPortal.repository.jobs.JobsRepository;
import com.applicantportal.ApplicantPortal.service.EntityFieldsFetcher;
import com.applicantportal.ApplicantPortal.service.PaginationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
@Service
public class JobServiceImpl implements JobsService{
    @Autowired
    PaginationService paginationService;
    @Autowired
    EntityFieldsFetcher entityFieldsFetcher;
    @Autowired
    JobsRepository jobsRepository;
    @Override
    public String showOnePage(int pageNo, String sortField, String sortDir, int size, Model model) {
        int pageSize = size;
        Page<Jobsdto> page = paginationService.getPaginationAndSort(pageNo, pageSize, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Jobsdto> showAllJobsList = page.getContent();
        model.addAttribute("jobsDtoDropdown", entityFieldsFetcher.getFields(Jobsdto.class));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("showAllJobs", showAllJobsList);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("size", size);
        return "showjobs";
    }

    @Override
    public ModelAndView addJobs(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("add-edit-jobs-form");
        Jobsdto newJobsdto = new Jobsdto();
        mav.addObject("addJobObject", newJobsdto);
        return mav;
    }

    @Override
    public String saveJob(Jobsdto jobsdto) throws IOException {
        jobsRepository.saveJobs(jobsdto);
        return "redirect:/alljobs";
    }

    @Override
    public ModelAndView showUpdateForm(int id) {
        ModelAndView mav = new ModelAndView("add-edit-jobs-form");
        Jobsdto jobsdto = jobsRepository.getJobById(id);
        mav.addObject("addJobObject", jobsdto);
        return mav;
    }

    @Override
    public String deleteJob(int id) {
        jobsRepository.deleteJobById(id);
        return "redirect:/alljobs";
    }

    @Override
    public String showDetails(int id, Model model) {
        model.addAttribute("details", jobsRepository.getJobById(id)) ;
        return "/job-details";
    }

    @Override
    public String search(String keyword, String columnName, Model model) {
        model.addAttribute("showAllJobs", jobsRepository.searchJobsByColumn(keyword, columnName));
        model.addAttribute("jobsDtoDropdown", entityFieldsFetcher.getFields(Jobsdto.class));
        return "showjobs";

    }

    @Autowired
    JobsDemoRepo jobsDemoRepo;
    public ModelAndView getHome(ModelAndView model){
        Page<Jobsdto> jobsdtoPage = jobsDemoRepo.findAll(PageRequest.of(1, 4));
        model.addObject("showAllJobs",jobsdtoPage );
        return model;
    }


}
