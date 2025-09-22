package com.finance_tracker.backend.services;

import com.finance_tracker.backend.dtos.CreateTransactionDto;
import com.finance_tracker.backend.dtos.UpdateTransactionDto;
import com.finance_tracker.backend.models.Category;
import com.finance_tracker.backend.models.Transaction;
import com.finance_tracker.backend.models.User;
import com.finance_tracker.backend.repositories.CategoryRepository;
import com.finance_tracker.backend.repositories.TransactionRepository;
import com.finance_tracker.backend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction with id " + id + " not found"));
    }

    public Transaction createTransaction(CreateTransactionDto dto) {
        User user = null;
        if (dto.getUserId() != null) {
            user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User with id " + dto.getUserId() + " not found"));
        }

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + dto.getCategoryId() + " not found"));

        Transaction transaction = Transaction.builder()
                .date(dto.getDate())
                .user(user)
                .category(category)
                .type(dto.getType())
                .amount(dto.getAmount())
                .description(dto.getDescription())
                .build();

        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, UpdateTransactionDto dto) {
        Transaction transaction = getTransaction(id);

        if (dto.getDate() != null) transaction.setDate(dto.getDate());
        if (dto.getAmount() != null) transaction.setAmount(dto.getAmount());
        if (dto.getDescription() != null) transaction.setDescription(dto.getDescription());
        if (dto.getType() != null) transaction.setType(dto.getType());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User with id " + dto.getUserId() + " not found"));
            transaction.setUser(user);
        }

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category with id " + dto.getCategoryId() + " not found"));
            transaction.setCategory(category);
        }

        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        Transaction transaction = getTransaction(id);
        transactionRepository.delete(transaction);
    }
}
