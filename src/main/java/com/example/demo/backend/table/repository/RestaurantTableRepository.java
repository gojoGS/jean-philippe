package com.example.demo.backend.table.repository;

import com.example.demo.backend.table.core.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
}
