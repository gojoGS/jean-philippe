package com.example.demo.security.user.enduser.service.user;

import com.example.demo.security.user.enduser.core.EndUser;
import com.example.demo.security.user.enduser.repository.EndUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EndUserServiceImpl implements EndUserService{
    @Autowired
    EndUserRepository endUserRepository;

    @Override
    public Optional<EndUser> getUser(String userId) {
        return endUserRepository.getEndUserByUserId(userId);
    }
}
