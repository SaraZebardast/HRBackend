package com.example.HRproject.service;

import com.example.HRproject.entity.PersonnelEntity;
import com.example.HRproject.entity.UserEntity;
import com.example.HRproject.repository.PersonnelRepository;
import com.example.HRproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PersonnelRepository personnelRepository;

    @Autowired
    public UserService(UserRepository userRepository, PersonnelRepository personnelRepository) {
        this.userRepository = userRepository;
        this.personnelRepository = personnelRepository;
    }

    public UserEntity createUser(UserEntity user) {
        if (user.getPersonnelId() != null) {
            Optional<PersonnelEntity> personnel = personnelRepository.findById(user.getPersonnelId());
            personnel.ifPresent(user::setPersonnel);
        }
        // Set a default role if none provided not sure if needed ngl
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        return userRepository.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
