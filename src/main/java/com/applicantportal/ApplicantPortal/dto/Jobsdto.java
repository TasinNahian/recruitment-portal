package com.applicantportal.ApplicantPortal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.copyValueOf;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobs")
public class Jobsdto implements Serializable {
    @Id
    private int job_id;
    private String job_title;
    private String position_title;
    private String reports_to;
    private String job_overview;
    private String initial_post_date;
    private String job_edit_date;
    private String job_deadline;
    private String responsibilities;
    private String qualifications;
    //add vacancy column


}
