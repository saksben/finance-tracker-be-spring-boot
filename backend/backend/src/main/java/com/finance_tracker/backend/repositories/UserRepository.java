package com.finance_tracker.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance_tracker.backend.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}