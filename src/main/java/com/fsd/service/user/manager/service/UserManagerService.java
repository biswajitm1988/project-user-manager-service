package com.fsd.service.user.manager.service;

import com.fsd.service.user.manager.entity.User;
import com.fsd.service.user.manager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserManagerService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        log.info("Getting All Users from Database");
        return repository.findAll();
    }

    public User saveUser(User task) {
        log.info("Saving the User into Database");
        return repository.save(task);
    }

    public Optional<User> getUserById(Long id) {
        log.info("Getting the User by Id from Database for {}",id);
        return repository.findById(id);
    }

    public void deleteUser(User task) {
        log.info("Deleting the User from Database for {}",task.getUserId());
        repository.delete(task);
    }

    public void deleteUserById(Long id) {
        log.info("Deleting the User from Database for {}",id);
        repository.deleteById(id);
    }
}
