package com.example.demo.security.user.enduser.service.details;

import com.example.demo.security.user.enduser.core.EndUser;
import com.example.demo.security.user.enduser.service.user.EndUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EndUserDetailsServiceImpl implements EndUserDetailsService{
    @Autowired
    EndUserService endUserService;

    @Override
    public EndUser getUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = "";

        // TOPIC pattern matching
        if (principal instanceof UserDetails userDetails) {
            userId = userDetails.getUsername();
        }

        var user =
                endUserService.getUser(userId);

        if (user.isEmpty()) {
            log.error("fuck up m8");
            throw new RuntimeException(String.format("User with email %s was not found", userId));
        } else {
            return user.get();
        }

    }
}
