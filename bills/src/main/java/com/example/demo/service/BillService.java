package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.dao.Bill;

public interface BillService {
    Bill saveBill(Bill bill);
    Bill getBillById(Long id);
    List<Bill> getAllBills();
    List<Bill> getBillsByDateRange(LocalDateTime start, LocalDateTime end);
    List<Bill> getBillsByUsername(String username);
    Bill updateBill(Long id, Bill bill);
    void deleteBill(Long id);
}