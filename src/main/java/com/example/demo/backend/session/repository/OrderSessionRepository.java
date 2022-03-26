package com.example.demo.backend.session.repository;

import com.example.demo.backend.session.core.OrderSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSessionRepository extends JpaRepository<OrderSession, Long> {
}
