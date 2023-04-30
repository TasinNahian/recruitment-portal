package com.applicantportal.ApplicantPortal.repository.jobs;

import com.applicantportal.ApplicantPortal.dto.Jobsdto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface JobsPaginationRepository extends PagingAndSortingRepository<Jobsdto, Integer> {
//    public List<Jobsdto> findAllJobs(Pageable pageable, String sortField);

}
