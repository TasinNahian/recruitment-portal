package com.applicantportal.ApplicantPortal.repository.jobs;

import com.applicantportal.ApplicantPortal.dto.Jobsdto;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface JobsDemoRepo  extends PagingAndSortingRepository<Jobsdto, Integer> {
//    @Query(value = "select * from applicant_tracking_portaldb.jobs where job_title like concat('%', :job_title, '%') and job_deadline like concat('%', :job_deadline, '%')")
//    public List<Jobsdto> searchJobsByTitleAndDeadline(@Param("job_title") String job_title, @Param("job_deadline") String job_deadline);

//    @Query(value = "select * from applicant_tracking_portaldb.jobs j where column_name= :columnName and" +
//            ":columnName like concat('%', :keyword, '%')")
//    public List<Jobsdto> searchJobsByTitleAndDeadline(@Param("keyword") String keyword, @Param("columnName") String columnName);

}

