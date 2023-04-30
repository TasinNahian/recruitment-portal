package com.applicantportal.ApplicantPortal.repository.responsibility;

import com.applicantportal.ApplicantPortal.dto.Responsibilities;
import com.applicantportal.ApplicantPortal.repository.responsibility.ResponsibilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResponsibilitiesRepositoryImpl implements ResponsibilitiesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_RESPONSIBILITY_QUERY =
            "INSERT INTO responsibilities(respon_desc) VALUES (?)";

    private static final String UPDATE_RESPONSIBILITY_QUERY =
            "UPDATE responsibilities SET respon_desc = ? WHERE respon_id = ?";

    private static final String GET_RESPONSIBILITY_BY_ID_QUERY =
            "SELECT * FROM responsibilities WHERE respon_id = ?";

    private static final String DELETE_RESPONSIBILITY_BY_ID_QUERY =
            "DELETE FROM responsibilities WHERE respon_id = ?";

    private static final String GET_ALL_RESPONSIBILTIES =
            "SELECT * FROM responsibilities";
    @Override
    public Responsibilities saveResponsibilities(Responsibilities responsibilities) {
        jdbcTemplate.update(INSERT_RESPONSIBILITY_QUERY, responsibilities.getRespon_desc());
        return responsibilities;
    }

    @Override
    public Responsibilities updateResponsibilities(Responsibilities responsibilities) {
        jdbcTemplate.update(UPDATE_RESPONSIBILITY_QUERY, responsibilities.getRespon_desc(),responsibilities.getId());
        return responsibilities;
    }

    @Override
    public Responsibilities getById(int id) {
        return jdbcTemplate.queryForObject(GET_RESPONSIBILITY_BY_ID_QUERY,(rs, rowNum) ->{
            return new Responsibilities(rs.getInt("respon_id"), rs.getString("respon_desc"));
            }, id
                );
    }

    @Override
    public String deleteById(int id) {
        jdbcTemplate.update(DELETE_RESPONSIBILITY_BY_ID_QUERY, id);
        return "User is deleted";
    }

    @Override
    public List<Responsibilities> allResponsibilities() {

        return jdbcTemplate.query(GET_ALL_RESPONSIBILTIES,(rs, rowNum) -> {
            return new Responsibilities(rs.getInt("respon_id"),rs.getString("respon_desc"));
        });
    }
}
