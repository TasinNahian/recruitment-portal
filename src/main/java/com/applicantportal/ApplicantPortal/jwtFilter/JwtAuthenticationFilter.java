package com.applicantportal.ApplicantPortal.jwtFilter;

import com.applicantportal.ApplicantPortal.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {


        String jwt = null;

        // Check if the "jwtToken" cookie is present in the request
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    System.out.println("JWT received: "+jwt);
                    break;
                }
            }
        }
        //checking the token format
        if(jwt == null){
            /**
             * if the authHeader in the request is  null and authHeader doesn't start
             * with "Bearer " then we call the filterChain to pass the request and response
             * to the next filter which will reject it and return;
             */
            filterChain.doFilter(request, response);
            return;
        }
        if(jwt !=null){
//            final String token = jwt.substring(7);
            final String userEmail = jwtTokenUtil.getUsernameFromToken(jwt);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                if (jwtTokenUtil.isTokenValid(jwt, userDetails)) {

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

                filterChain.doFilter(request, response);

            }
        }




//        final String authHeader = request.getHeader("Authorization");
//        //checking the token format
//        if(authHeader == null || !authHeader.startsWith("Bearer ")){
//            /**
//             * if the authHeader in the request is  null and authHeader doesn't start
//             * with "Bearer " then we call the filterChain to pass the request and response
//             * to the next filter which will reject it and return;
//             */
//            filterChain.doFilter(request, response);
//            return;
//        }
//        if(authHeader !=null && authHeader.startsWith("Bearer ")){
//            final String jwt = authHeader.substring(7);
//            final String userEmail = jwtTokenUtil.getUsernameFromToken(jwt);
//            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
//
//                if (jwtTokenUtil.isTokenValid(jwt, userDetails)) {
//
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
////                response.setHeader("Authorization", "Bearer " + jwtTokenUtil.generateToken(userDetails));
//
//                filterChain.doFilter(request, response);
//
//            }
//        }
//
    }
}
