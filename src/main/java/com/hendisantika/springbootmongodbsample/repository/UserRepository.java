package com.hendisantika.springbootmongodbsample.repository;

import com.hendisantika.springbootmongodbsample.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongodb-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-25
 * Time: 06:18
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}