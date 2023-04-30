package com.applicantportal.ApplicantPortal.dto;

import jakarta.annotation.Generated;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "Users")
public class User {
    @Id
    private int user_id;
    private String email;
    private String pass;
    private String role;
}
