package com.applicantportal.ApplicantPortal.repository.jobs;

import com.applicantportal.ApplicantPortal.dto.Jobsdto;
import com.applicantportal.ApplicantPortal.entity.Jobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class JobsRepositoryImpl implements JobsRepository {

//    private static final String INSERT_JOBS_QUERY =
//            "INSERT INTO jobs(job_id, job_title, position_title, reports_to, job_overview, initial_post_date, job_edit_date, job_deadline," +
//                    " responsibilities, qualifications) VALUES (?,?,?,?,?,CURDATE(),CURDATE(),?,?,?)" +
//                    " ON DUPLICATE KEY UPDATE job_id=LAST_INSERT_ID(job_id), job_title=VALUES(job_title), " +
//                    "position_title=VALUES(position_title), reports_to=VALUES(reports_to), job_overview=VALUES(job_overview), " +
//                    "job_edit_date=CURDATE(), job_deadline=VALUES(job_deadline), responsibilities=VALUES(responsibilities), " +
//                    "qualifications=VALUES(qualifications)";
//    private static final String UPDATE_JOBS_QUERY =
//            "UPDATE jobs SET job_title= ?, position_title = ?, reports_to = ?, job_overview = ?, job_edit_date = CURDATE(), job_deadline = ? WHERE job_id = ?";
//
//    private static final String GET_JOBS_BY_ID_QUERY =
//            "SELECT * FROM jobs WHERE job_id = ?";
//
//    private static final String DELETE_JOBS_BY_ID_QUERY =
//            "DELETE FROM jobs WHERE job_id = ?";
//
//    private static final String GET_ALL_JOBS =
//            "SELECT * FROM jobs";
    @Autowired
    JdbcTemplate jdbcTemplate;

    Query query;
    @Override
    public void saveJobs(Jobsdto jobsdto) {
        jdbcTemplate.update(query.INSERT_JOBS.getStatement(), jobsdto.getJob_id(), jobsdto.getJob_title(), jobsdto.getPosition_title(), jobsdto.getReports_to(),
                jobsdto.getJob_overview(), jobsdto.getJob_deadline(), jobsdto.getResponsibilities(), jobsdto.getQualifications());
    }
    @Override
    public void updateJobs(Jobsdto jobsdto) {
        jdbcTemplate.update(query.UPDATE_JOBS.getStatement(), jobsdto.getJob_title(), jobsdto.getPosition_title(), jobsdto.getReports_to(),
                jobsdto.getJob_overview(), jobsdto.getJob_deadline(), jobsdto.getJob_id());
    }
    @Override
    public Jobsdto getJobById(int id) {
        return jdbcTemplate.queryForObject(query.GET_JOBS_BY_ID.getStatement(), (rs, rowNum) -> {
            return new Jobsdto(rs.getInt("job_id"),
                    rs.getString("job_title"),
                    rs.getString("position_title"),
                    rs.getString("reports_to"),
                    rs.getString("job_overview"),
                    rs.getString("initial_post_date"),
                    rs.getString("job_edit_date"),
                    rs.getString("job_deadline"),
                    rs.getString("responsibilities"),
                    rs.getString("qualifications"));
                }, id
        );

    }
    @Override
    public void deleteJobById(int id) {
        jdbcTemplate.update(Query.DELETE_JOBS_BY_ID.getStatement(), id);
    }
    @Override
    public List<Jobsdto> allJobs() {
        System.out.println("allJobs method is called");
        return jdbcTemplate.query(query.GET_ALL_JOBS.getStatement(), (rs, rowNum) -> {
                    return new Jobsdto(rs.getInt("job_id"),
                            rs.getString("job_title"),
                            rs.getString("position_title"),
                            rs.getString("reports_to"),
                            rs.getString("job_overview"),
                            rs.getString("initial_post_date"),
                            rs.getString("job_edit_date"),
                            rs.getString("job_deadline"),
                            rs.getString("responsibilities"),
                            rs.getString("qualifications"));
                }
        );
//        return jdbcTemplate.queryForList(query.GET_ALL_JOBS.getStatement());
    }
    @Override
    public List<Jobsdto> searchJobsByColumn(String keyword, String columnName) {
        String SEARCH_JOBS = "select * from applicant_tracking_portaldb.jobs where "+ columnName+ " like '%"+ keyword + "%';";
        return jdbcTemplate.query(SEARCH_JOBS, new BeanPropertyRowMapper(Jobsdto.class));
    }
    @Override
    public Page getTotalPaginatedJobs(Sort.Order order, Pageable pageable) {
        String rowCountSql = "SELECT COUNT(*) FROM jobs";
        int total = jdbcTemplate.queryForObject(rowCountSql,Integer.class);

//        List<Jobsdto> rows1 = jdbcTemplate.query("SELECT * FROM jobs ORDER BY " + order.getProperty() + " "
//                        + order.getDirection().name() + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset(),new BeanPropertyRowMapper(Jobsdto.class));
        String query = "SELECT * FROM jobs ORDER BY " + order.getProperty() + " "
                + order.getDirection().name() + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);




//                new RowMapper<Jobsdto>() {
//                    public Jobsdto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        return new Jobsdto(rs.getInt("job_id"),
//                                rs.getString("job_title"),
//                                rs.getString("position_title"),
//                                rs.getString("reports_to"),
//                                rs.getString("job_overview"),
//                                rs.getString("initial_post_date"),
//                                rs.getString("job_edit_date"),
//                                rs.getString("job_deadline"),
//                                rs.getString("responsibilities"),
//                                rs.getString("qualifications"));
//
//                    }
//                });

//        return new PageImpl<Jobsdto>(rows, pageable, total);
        return new PageImpl(rows,pageable,total);

    }

}

