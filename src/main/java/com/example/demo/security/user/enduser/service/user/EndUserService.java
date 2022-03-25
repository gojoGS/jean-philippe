package com.example.demo.security.user.enduser.service.user;

import com.example.demo.security.user.enduser.core.EndUser;

import java.util.Optional;

public interface EndUserService {
    Optional<EndUser> getUser(String userId);
}
