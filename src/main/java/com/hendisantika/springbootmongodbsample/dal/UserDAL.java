package com.hendisantika.springbootmongodbsample.dal;

import com.hendisantika.springbootmongodbsample.model.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongodb-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-25
 * Time: 06:31
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAL {

    List<User> getAllUsers();

    User getUserById(String userId);

    User addNewUser(User user);

    Object getAllUserSettings(String userId);

    String getUserSetting(String userId, String key);

    String addUserSetting(String userId, String key, String value);
}