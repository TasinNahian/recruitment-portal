package com.applicantportal.ApplicantPortal.controller.jobs;

import ch.qos.logback.classic.spi.EventArgUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder {
    public static void main(String[] args) {
        String pass = new BCryptPasswordEncoder().encode("admin");
        System.out.println(pass);
        /*
        tasin.nahian@gmail.com -> 12345
        omar.afridi@yahoo.com -> omar
        yasir.khan@gmail.com -> yasir
        super.admin@gmail.com -> admin
         */
    }
}
