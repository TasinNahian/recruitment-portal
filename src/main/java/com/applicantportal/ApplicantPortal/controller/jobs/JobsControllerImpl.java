package com.applicantportal.ApplicantPortal.controller.jobs;

import com.applicantportal.ApplicantPortal.jwtFilter.JwtAuthenticationFilter;
import com.applicantportal.ApplicantPortal.jwtFilter.JwtTokenUtil;
import com.applicantportal.ApplicantPortal.repository.jobs.JobsRepository;
import com.applicantportal.ApplicantPortal.dto.Jobsdto;
import com.applicantportal.ApplicantPortal.service.jobs.JobsService;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class JobsControllerImpl implements JobsController {
    private final JobsService jobsService;

    @Override
    public String showAllJobs(Model model) {
        return showJobsOnePage(1,"job_id","asc",5, model);
    }
    @Override
    public String showJobsOnePage(int pageNo, String sortField, String sortDir, int size, Model model) {
        return jobsService.showOnePage(pageNo, sortField, sortDir, size, model);
    }
    @Override
    public ModelAndView addJobs(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        return jobsService.addJobs(request, response);
    }
    @Override
    public String saveJob(Jobsdto jobsdto) throws IOException {
        return jobsService.saveJob(jobsdto);
    }
    @Override
    public ModelAndView showUpdateForm( int id) {
       return jobsService.showUpdateForm(id);
    }
    @Override
    public String deleteJob( int id) {
        return jobsService.deleteJob(id);
    }
    @Override
    public String showDetails( int id, Model model){
        return jobsService.showDetails(id, model);
    }
    @Override
    public String search( String keyword, String columnName, Model model){
       return jobsService.search(keyword, columnName, model);
    }
    @Override
    public String getPageSize(int size, Model model) {
        return showJobsOnePage(1,"job_id","asc",size, model);
    }
    @Override
    public ModelAndView home(ModelAndView model){
        return jobsService.getHome(model);
    }

}
