package com.applicantportal.ApplicantPortal.config;

import com.applicantportal.ApplicantPortal.jwtFilter.CustomAuthenticationEntryPoint;
import com.applicantportal.ApplicantPortal.jwtFilter.JwtAuthenticationFilter;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

//    @Bean
//    public UserDetailsService getUserDetailsService(){
//        return userDetailsServiceImpl;
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    protected AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        //pass the authenticationProvider to authenticationBuilder
        return configuration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                .csrf().disable()
                 .authorizeHttpRequests()
                 .requestMatchers("/js/**", "/styles/**").permitAll()
                 .requestMatchers("/api/login/**").permitAll()
                 .requestMatchers("/addjob").hasRole("SUPER_ADMIN")
                 .requestMatchers("/home").hasRole("USER")
                 .anyRequest().authenticated()
                 .and()
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
                 .authenticationProvider(daoAuthenticationProvider())
                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                 .exceptionHandling()
                 .authenticationEntryPoint(customAuthenticationEntryPoint());


         http.formLogin()
                 .loginPage("/api/login/get")
                 .usernameParameter("username")
                 .passwordParameter("password")
                 .and()
                 .logout().logoutUrl("/logout").deleteCookies("Authorization").clearAuthentication(true).invalidateHttpSession(true);
         DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
       return defaultSecurityFilterChain;

    }
}
