package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final UserRepository userRepo;
    final UserProducer userProducer;

    public UserService(UserRepository userRepo, UserProducer userProducer) {
        this.userRepo = userRepo;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel){
        userModel = userRepo.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }
}
