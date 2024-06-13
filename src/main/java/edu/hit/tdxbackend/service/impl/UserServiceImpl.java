package edu.hit.tdxbackend.service.impl;

import edu.hit.tdxbackend.entity.User;
import edu.hit.tdxbackend.mapper.UserMapper;
import edu.hit.tdxbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDAO;
    @Override
    public User login(String username, String password) {
        return userDAO.getUserByUsernameAndPassword(username, password);
    }
    @Override
    public boolean register(User user) throws IOException {
        // TODO need to write
        return false;
    }

    @Override
    public List<User> getUserList() {
        return userDAO.getUserList();
    }

    @Override
    public boolean deleteUser(int id) {
        return userDAO.deleteUserById(id)!=0;
    }

}
