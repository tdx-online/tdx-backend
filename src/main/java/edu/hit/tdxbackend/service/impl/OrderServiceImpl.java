package edu.hit.tdxbackend.service.impl;

import edu.hit.tdxbackend.entity.Order;
import edu.hit.tdxbackend.entity.OrderItem;
import edu.hit.tdxbackend.mapper.OrderMapper;
import edu.hit.tdxbackend.mapper.ProductMapper;
import edu.hit.tdxbackend.mapper.UserMapper;
import edu.hit.tdxbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, ProductMapper productMapper) {
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
    }


    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public List<Order> getOrdersByUserId(Integer userId) throws IOException {
        return orderMapper.getOrdersByUserId(userId);
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) throws IOException {
        List<OrderItem> orderItems = orderMapper.getOrderItemsByOrderId(orderId);
        for(OrderItem orderItem : orderItems) {
            orderItem.setProductImage(productMapper.getOneImageByProductId(orderItem.getPid()));
        }
        return orderItems;
    }

    @Override
    public boolean updateStatus(Integer oid, int status) throws IOException {
        return orderMapper.updateStatus(oid, status + 1);
    }

    @Override
    public boolean deleteOrder(Integer oid) throws IOException {
        return orderMapper.deleteOrder(oid);
    }

    @Override
    public boolean createOrder(Order order) throws IOException {
        boolean flag = orderMapper.createOrder(order);
        if(!flag) {
            return false;
        }else{
            return orderMapper.createOrderItems(order.getId(), order.getOrderItems());
        }
    }


}
