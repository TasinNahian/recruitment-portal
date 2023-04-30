package com.applicantportal.ApplicantPortal.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntityFieldsFetcher {

    public List<String> getFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> variableNames = new ArrayList<>();
        for (Field field : fields) {
            variableNames.add(field.getName());
        }
        return variableNames;
    }

    }
