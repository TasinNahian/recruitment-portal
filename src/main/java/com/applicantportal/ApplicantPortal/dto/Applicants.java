package com.applicantportal.ApplicantPortal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applicants {
    private int applicant_id;
    private String applicant_fname;
    private String applicant_lname;
    private String street_name;
    private String house_no;
    private String city;
    private String division;
    private int postal_code;
    private int phone_number;
    private InputStream resumee;
    private InputStream image;
    private String email;
    private String pass;
}
