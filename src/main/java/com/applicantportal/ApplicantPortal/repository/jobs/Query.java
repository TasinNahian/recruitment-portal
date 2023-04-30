package com.applicantportal.ApplicantPortal.repository.jobs;

import org.springframework.stereotype.Component;

public enum Query {
    INSERT_JOBS,
    UPDATE_JOBS,
    GET_JOBS_BY_ID,
    DELETE_JOBS_BY_ID,
    GET_ALL_JOBS;

    public String getStatement(){
        switch(this){
            case INSERT_JOBS:
                return "INSERT INTO jobs(job_id, job_title, position_title, reports_to, job_overview, initial_post_date, job_edit_date, job_deadline," +
                        " responsibilities, qualifications) VALUES (?,?,?,?,?,CURDATE(),CURDATE(),?,?,?)" +
                        " ON DUPLICATE KEY UPDATE job_id=LAST_INSERT_ID(job_id), job_title=VALUES(job_title), " +
                        "position_title=VALUES(position_title), reports_to=VALUES(reports_to), job_overview=VALUES(job_overview), " +
                        "job_edit_date=CURDATE(), job_deadline=VALUES(job_deadline), responsibilities=VALUES(responsibilities), " +
                        "qualifications=VALUES(qualifications)";
            case UPDATE_JOBS:
                return "UPDATE jobs SET job_title= ?, position_title = ?, reports_to = ?, job_overview = ?, job_edit_date = CURDATE(), job_deadline = ? WHERE job_id = ?";

            case GET_JOBS_BY_ID:
                return "SELECT * FROM jobs WHERE job_id = ?";
            case DELETE_JOBS_BY_ID:
                return "DELETE FROM jobs WHERE job_id = ?";
            case  GET_ALL_JOBS:
                return "SELECT * FROM jobs";
            default:
                return null;
        }
    }
}
