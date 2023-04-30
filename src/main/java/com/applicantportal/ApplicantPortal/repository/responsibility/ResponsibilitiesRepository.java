package com.applicantportal.ApplicantPortal.repository.responsibility;

import com.applicantportal.ApplicantPortal.dto.Responsibilities;

import java.util.List;

public interface ResponsibilitiesRepository {

    Responsibilities saveResponsibilities(Responsibilities responsibilities);
    Responsibilities updateResponsibilities(Responsibilities responsibilities);
    Responsibilities getById(int id);
    String deleteById(int id);
    List<Responsibilities> allResponsibilities();
}
