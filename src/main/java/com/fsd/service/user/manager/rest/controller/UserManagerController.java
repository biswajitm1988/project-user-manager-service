package com.fsd.service.user.manager.rest.controller;

import com.fsd.service.user.manager.entity.User;
import com.fsd.service.user.manager.service.UserManagerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user/manager")
public class UserManagerController {

    @Autowired
    private UserManagerService service;

    @ApiOperation(value = "Fetches all users from the database.", response = User.class)
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        log.info("UserManagerController >> getAllUsers >> ");
        List<User> users = service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "Fetch user by the provided id from the database.", response = User.class)
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") Long id) throws Exception {
        log.info("UserManagerController >> getUserById >> "+id);
        Optional<User> user = service.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Add the new user into the database and return new user.", response = User.class)
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
        log.info("UserManagerController >> addUser >> {}", user);
        User newUser = service.saveUser(user);
        log.info("User Added. New Id {} and user {}",newUser.getUserId(),newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update the new user into the database and return updated user", response = User.class)
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception {
        log.info("UserManagerController >> updateUser >> {}", user);
        User updatedUser = service.saveUser(user);
        log.info("User Updated. New Id {} and user {}",updatedUser.getUserId(), updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete User. Test Purpose Only")
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") Long id) throws Exception {
        log.info("UserManagerController >> deleteUser >> {}", id);
        service.deleteUserById(id);
        log.info("User Deleted.");
    }
}