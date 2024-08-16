package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Bill;
import com.example.demo.service.BillService;

@RestController
@RequestMapping("/api/bills")
public class BillsController {

    @Autowired
    private BillService billService;

    @PreAuthorize("hasRole('PRODUCER')")
    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return billService.saveBill(bill);
    }

    @PreAuthorize("hasAnyRole('PRODUCER', 'CONSUMER')")
    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @PreAuthorize("hasAnyRole('PRODUCER', 'CONSUMER')")
    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @PreAuthorize("hasRole('PRODUCER')")
    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        return billService.updateBill(id, bill);
    }

    @PreAuthorize("hasRole('PRODUCER')")
    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
    }
}
