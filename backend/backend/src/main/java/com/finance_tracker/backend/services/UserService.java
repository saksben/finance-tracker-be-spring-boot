package com.finance_tracker.backend.services;

import com.finance_tracker.backend.dtos.*;
import com.finance_tracker.backend.models.User;
import com.finance_tracker.backend.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public User createUser(CreateUserDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .build();
        return userRepository.save(user);
    }

    public User updateUser(Long id, UpdateUserDto dto) {
        User user = getUser(id);
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
