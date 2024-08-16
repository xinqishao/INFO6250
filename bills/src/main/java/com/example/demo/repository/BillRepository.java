package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Bill> findByUserUsername(String username);
}