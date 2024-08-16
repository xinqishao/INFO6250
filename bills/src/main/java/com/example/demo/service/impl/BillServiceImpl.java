package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Bill;
import com.example.demo.repository.BillRepository;
import com.example.demo.service.BillService;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + id));
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> getBillsByDateRange(LocalDateTime start, LocalDateTime end) {
        return billRepository.findByCreatedAtBetween(start, end);
    }

    @Override
    public List<Bill> getBillsByUsername(String username) {
        return billRepository.findByUserUsername(username);
    }

    @Override
    public Bill updateBill(Long id, Bill bill) {
        Bill existingBill = getBillById(id);
        existingBill.setDescription(bill.getDescription());
        existingBill.setAmount(bill.getAmount());
        existingBill.setCreatedAt(bill.getCreatedAt());
        return billRepository.save(existingBill);
    }

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }
}