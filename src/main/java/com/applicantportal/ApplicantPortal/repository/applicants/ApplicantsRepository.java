package com.applicantportal.ApplicantPortal.repository.applicants;

import com.applicantportal.ApplicantPortal.dto.Applicants;

import java.util.List;

public interface ApplicantsRepository {

    Applicants createApplicant(Applicants applicants);
    List<Applicants> allApplicants();
    Applicants getApplicantById(int id);
    Applicants updateApplicants(Applicants applicants);
    String deleteApplicantById(int id);
}
