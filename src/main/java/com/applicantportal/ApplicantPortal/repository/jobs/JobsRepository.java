package com.applicantportal.ApplicantPortal.repository.jobs;

import com.applicantportal.ApplicantPortal.dto.Jobsdto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface JobsRepository {
    void saveJobs(Jobsdto jobsdto);
    void updateJobs(Jobsdto jobsdto);
    Jobsdto getJobById(int id);
    void deleteJobById(int id);

    List<Jobsdto> allJobs();
    List<Jobsdto> searchJobsByColumn(String job_title, String job_deadline);
    Page getTotalPaginatedJobs(Sort.Order order, Pageable pageable);

}
