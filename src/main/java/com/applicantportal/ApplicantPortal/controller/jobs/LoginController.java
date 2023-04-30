package com.applicantportal.ApplicantPortal.controller.jobs;

import com.applicantportal.ApplicantPortal.config.CustomUserDetails;
import com.applicantportal.ApplicantPortal.dto.User;
import com.applicantportal.ApplicantPortal.jwtFilter.JwtTokenUtil;
import com.applicantportal.ApplicantPortal.repository.user.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.templateparser.markup.HTMLTemplateParser;

import java.util.Collection;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    @GetMapping("/get")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @PostMapping("/post")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        System.out.println("in postmapping");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password")));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Cookie cookie = new Cookie("Authorization", jwtTokenUtil.generateToken(userDetails));
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(request.isSecure());
        cookie.setMaxAge(3600); // Cookie expires in 1 hour
        System.out.println(cookie);
        response.addCookie(cookie);
        System.out.println(cookie.getValue());

        return "redirect:/addjob";
    }
    @GetMapping("/secretKey")
    public String getSecretKey() {
        return "mySecretKey"; // replace with your actual secret key
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        return " You have been logged out";
    }

}
