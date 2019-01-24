package com.hendisantika.springbootmongodbsample.controller;

import com.hendisantika.springbootmongodbsample.model.User;
import com.hendisantika.springbootmongodbsample.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
