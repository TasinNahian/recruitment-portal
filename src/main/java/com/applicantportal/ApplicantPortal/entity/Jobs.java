package com.applicantportal.ApplicantPortal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jobs {
    private String job_title;
    private String position_title;
    private String reports_to;
    private String job_overview;
    private String job_deadline;


}
