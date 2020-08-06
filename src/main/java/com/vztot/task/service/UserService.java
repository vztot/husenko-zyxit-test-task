package com.vztot.task.service;

import com.vztot.task.entity.User;
import java.util.List;

public interface UserService {
    User findUserByLogin(String login);

    User saveUser(User user);

    List<User> findAll();
}
