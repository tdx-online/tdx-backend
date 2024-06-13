package edu.hit.tdxbackend.service.impl;

import edu.hit.tdxbackend.entity.Order;
import edu.hit.tdxbackend.mapper.UserMapper;
import edu.hit.tdxbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    // TODO need to finish
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Order> getAllOrders(String address) throws IOException {
        return List.of();
    }

    @Override
    public List<Order> getOrdersByUserId(String addr, Integer userId) throws IOException {
        return List.of();
    }

    @Override
    public boolean updateStatus(String addr, Integer userId, String orderCode, Integer status) throws IOException {
        return false;
    }

    @Override
    public boolean deleteOrder(String addr, Integer userId, String orderCode) throws IOException {
        return false;
    }

    @Override
    public boolean createOrder(String addr, Order order) throws IOException {
        return false;
    }
}
