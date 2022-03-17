package com.example.demo.security.user.enduser.repository;

import com.example.demo.security.user.enduser.core.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnduserRepository extends JpaRepository<EndUser, Long> {
    Optional<EndUser> getEndUserByUserId(String userId);
}
