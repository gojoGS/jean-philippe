package com.example.demo.backend.beverage.repository;

import com.example.demo.backend.beverage.core.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Long> {
}
