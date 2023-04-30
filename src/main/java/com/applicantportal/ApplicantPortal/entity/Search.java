package com.applicantportal.ApplicantPortal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    private String keyword;
    private String columnName;
}
