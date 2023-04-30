package com.applicantportal.ApplicantPortal.repository.jobs;

import com.applicantportal.ApplicantPortal.dto.Jobsdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Sort;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class JobsPaginationRepositoryImpl {
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Override
//    public Iterable findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page findAll(Pageable pageable) {
//        return null;
//    }
//
//    public Page findAllJobs(Pageable pageable, String sortField) {
//        String rowCountSql = "SELECT COUNT(*) FROM jobs";
//        int total = jdbcTemplate.queryForObject(rowCountSql,Integer.class);
//
//        Sort.Order order = !pageable.getSort().isEmpty() ? pageable.getSort().toList().get(0) : Sort.Order.by(sortField);
//
//        List<Jobsdto> rows = jdbcTemplate.query("SELECT * FROM jobs ORDER BY " + order.getProperty() + " "
//                + order.getDirection().name() + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset(),
//                new RowMapper<Jobsdto>() {
//                    public Jobsdto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                            return new Jobsdto(rs.getInt("job_id"),
//                                    rs.getString("job_title"),
//                                    rs.getString("position_title"),
//                                    rs.getString("reports_to"),
//                                    rs.getString("job_overview"),
//                                    rs.getString("initial_post_date"),
//                                    rs.getString("job_edit_date"),
//                                    rs.getString("job_deadline"),
//                                    rs.getString("responsibilities"),
//                                    rs.getString("qualifications"));
//
//                    }
//                });
//        return new PageImpl<Jobsdto>(rows, pageable, total);
//    }
//
//    public Page findPage(int pageNo, int pageSize, String sortField, String sortDirection) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//                Sort.by(sortField).descending();
//
//        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
//        return findAllJobs(pageable,  sortField);
//    }
//
//    @Override
//    public Object save(Object entity) {
//        return null;
//    }
//
//    @Override
//    public Iterable saveAll(Iterable entities) {
//        return null;
//    }
//
//    @Override
//    public Optional findById(Object o) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Object o) {
//        return false;
//    }
//
//    @Override
//    public Iterable findAll() {
//        return null;
//    }
//
//    @Override
//    public Iterable findAllById(Iterable iterable) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Object o) {
//
//    }
//
//    @Override
//    public void delete(Object entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable iterable) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }

//    final private List<Jobsdto> jobsdtoList;
//
//    public Page<Jobsdto> finaPaginated(Pageable pageable){
//        int pageSize = pageable.getPageSize();
//        int currentPage = pageable.getPageSize();
//        int startItem = currentPage * pageSize;
//
//        List<Jobsdto> list;
//        if (jobsdtoList.size()<startItem){
//            list = Collections.emptyList();
//        }else{
//            int toIndex = Math.min(startItem + pageSize, jobsdtoList.size());
//            list = jobsdtoList.subList(startItem, toIndex);
//        }
//        Page<Jobsdto> jobsdtoPage= new PageImpl<Jobsdto>(list, PageRequest.of(currentPage, pageSize), jobsdtoList.size());
//        return jobsdtoPage;
//    }

}
