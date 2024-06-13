package edu.hit.tdxbackend.service;

import edu.hit.tdxbackend.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User login(String username, String password);
    boolean register(User user) throws IOException;
    List<User> getUserList();
    boolean deleteUser(int id);
}
