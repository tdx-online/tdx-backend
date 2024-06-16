package edu.hit.tdxbackend.service;

import edu.hit.tdxbackend.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    User login(String username, String password);

    /**
     * 注册
     *
     * @param user 用户信息
     * @return 是否注册成功
     */
    boolean register(User user);

    /**
     * 获取用户列表
     *
     * @return 用户列表
     */
    List<User> getUserList();

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    boolean deleteUser(int id);
}
