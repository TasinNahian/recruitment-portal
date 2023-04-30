package com.applicantportal.ApplicantPortal.repository.user;

import com.applicantportal.ApplicantPortal.dto.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface UserRepository extends CrudRepository<User, Integer> {

     User findUserByEmail(String email);
}
