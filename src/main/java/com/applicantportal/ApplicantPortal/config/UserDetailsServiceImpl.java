package com.applicantportal.ApplicantPortal.config;

import com.applicantportal.ApplicantPortal.dto.User;
import com.applicantportal.ApplicantPortal.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //fetch user from database
        User user = userRepository.findUserByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found.");
        }
        UserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
