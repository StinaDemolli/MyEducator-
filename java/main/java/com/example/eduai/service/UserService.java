
package com.example.eduai.service;

import com.example.eduai.model.User;
import com.example.eduai.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }
}
