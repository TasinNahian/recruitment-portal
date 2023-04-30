package com.applicantportal.ApplicantPortal.repository.applicants;

import com.applicantportal.ApplicantPortal.dto.Applicants;
import com.applicantportal.ApplicantPortal.repository.applicants.ApplicantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ApplicantsRepositoryImpl implements ApplicantsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_APPLICANTS_QUERY =
            "INSERT INTO applicant(" +
                    "applicant_fname, applicant_lname, street_name," +
                    "house_no, city, division, postal_code, phone_number," +
                    "resumee, image, email, pass ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE_APPLICANTS_QUERY =
            "UPDATE applicant SET applicant_fname = ? WHERE email = ?";

    private static final String GET_APPLICANTS_BY_ID_QUERY =
            "SELECT * FROM applicant WHERE applicant_id = ?";

    private static final String DELETE_APPLICANTS_BY_ID_QUERY =
            "DELETE FROM applicants WHERE applicant_id = ?";

    private static final String GET_ALL_APPLICANTS =
            "SELECT * FROM applicants";

    @Override
    public Applicants createApplicant(Applicants applicants) {
        return null;
    }

    @Override
    public List<Applicants> allApplicants() {
        return null;
    }

    @Override
    public Applicants getApplicantById(int id) {
        return null;
    }

    @Override
    public Applicants updateApplicants(Applicants applicants) {
        return null;
    }

    @Override
    public String deleteApplicantById(int id) {
        return null;
    }
}
