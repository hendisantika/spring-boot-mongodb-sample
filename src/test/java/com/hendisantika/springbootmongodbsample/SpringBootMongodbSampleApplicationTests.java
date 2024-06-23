package com.hendisantika.springbootmongodbsample;

import com.hendisantika.springbootmongodbsample.model.User;
import com.hendisantika.springbootmongodbsample.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpringBootMongodbSampleApplicationTests {
    static final GenericContainer mongo;

    static {
        mongo = new GenericContainer("mongo:8.0-rc-jammy")
                .withExposedPorts(27017)
                .waitingFor(Wait.forLogMessage(".*Waiting for connections.*\\n", 1))
                .withEnv("MONGO_INITDB_ROOT_USERNAME", "yuji")
                .withReuse(true)
                .withEnv("MONGO_INITDB_ROOT_PASSWORD", "S3cret");
    }

    static {
        Startables.deepStart(mongo).join();
    }

    @Autowired
    private UserRepository userRepository;

    @DynamicPropertySource
    static void mongoProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", mongo::getHost);
        registry.add("spring.data.mongodb.port", mongo::getFirstMappedPort);
        registry.add("spring.data.mongodb.database", () -> "app_db");
        registry.add("spring.data.mongodb.username", () -> "yuji");
        registry.add("spring.data.mongodb.password", () -> "S3cret");
    }

    @Test
    @DisplayName("Create User should be ok")
    @Order(1)
    public void testCreate() {
        Map<String, String> userSettings = new HashMap<>();
        userSettings.put("username", "suguru");
        var user = new User("user001", "geto", new Date(), userSettings);
        var savedUser = userRepository.save(user);

        Assertions.assertNotNull(savedUser.getUserId());
    }

}

