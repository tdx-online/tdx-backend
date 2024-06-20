package edu.hit.tdxbackend.service.impl;

import edu.hit.tdxbackend.config.authentication.JWTUtil;
import edu.hit.tdxbackend.entity.User;
import edu.hit.tdxbackend.mapper.UserMapper;
import edu.hit.tdxbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import shade.kotlin.Pair;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RedisTemplate<String, Object> redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Pair<User, String> login(String username, String password) {
        User user = userMapper.getUserByUsernameAndPassword(username, password);

        String token = null;
        if (user != null) {
            token = JWTUtil.generateToken(username, String.valueOf(user.getId()));
//            System.out.println("token: " + token);
            redisTemplate.opsForValue().set(token, user, 5, TimeUnit.MINUTES);
//            System.out.println(redisTemplate.opsForValue().get(token));
        }

        return new Pair<>(user, token);
    }

    @Override
    public boolean register(User user) {
        return userMapper.addUser(user) != 0;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public boolean deleteUser(int id) {
        return userMapper.deleteUserById(id) != 0;
    }

    @Override
    public User getUserByToken(String token) {
        token = token.replace("Bearer ", "");
        return (User) redisTemplate.opsForValue().get(token);
    }

    @Override
    public Boolean logout(String token) {
        return redisTemplate.delete(token);
    }

}
