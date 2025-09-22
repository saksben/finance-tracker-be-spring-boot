package com.finance_tracker.backend.controllers;

import com.finance_tracker.backend.dtos.CreateTransactionDto;
import com.finance_tracker.backend.dtos.UpdateTransactionDto;
import com.finance_tracker.backend.models.Transaction;
import com.finance_tracker.backend.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody CreateTransactionDto dto) {
        return transactionService.createTransaction(dto);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody UpdateTransactionDto dto) {
        return transactionService.updateTransaction(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Transaction with ID " + id + " deleted successfully");
    }
}
