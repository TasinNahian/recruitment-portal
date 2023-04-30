package com.applicantportal.ApplicantPortal.service.jobs;

import com.applicantportal.ApplicantPortal.dto.Jobsdto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public interface JobsService {
    String showOnePage(int pageNo,String sortField, String sortDir, int size, Model model);
    ModelAndView addJobs(HttpServletRequest request, HttpServletResponse response);
    String saveJob(Jobsdto jobsdto) throws IOException;
    ModelAndView showUpdateForm( int id);
    String deleteJob( int id);
    String showDetails( int id, Model model);
    String search( String keyword, String columnName, Model model);

    ModelAndView getHome(ModelAndView model);

}
