package com.hendisantika.springbootmongodbsample.controller;

import com.hendisantika.springbootmongodbsample.model.User;
import com.hendisantika.springbootmongodbsample.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongodb-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-25
 * Time: 06:20
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping(value = "/")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        LOG.info("Getting all users.");
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID: {}.", userId);
        return userRepository.findById(userId);
    }

    @PostMapping("/create")
    public User addNewUsers(@RequestBody User user) {
        LOG.info("Saving user.");
        return userRepository.save(user);
    }

    @GetMapping("/settings/{userId}")
    public Object getAllUserSettings(@PathVariable String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getUserSettings();
        } else {
            return "User not found.";
        }
    }

    @GetMapping("/settings/{userId}/{key}")
    public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getUserSettings().get(key);
        } else {
            return "User not found.";
        }
    }

    @GetMapping("/settings/{userId}/{key}/{value}")
    public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(user1 -> {
            user.get().getUserSettings().put(key, value);
            userRepository.save(user1);
        });
//
        return "Key added";
//        } else {
//            return "User not found.";
//        }

    }
}
