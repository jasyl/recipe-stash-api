package com.capstone.recipestashapi.controller;

import com.capstone.recipestashapi.exception.ResourceNotFoundException;
import com.capstone.recipestashapi.model.User;
import com.capstone.recipestashapi.repository.UserRepository;
import com.capstone.recipestashapi.security.CurrentUser;
import com.capstone.recipestashapi.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}