package edu.hit.tdxbackend.service;

import edu.hit.tdxbackend.entity.Order;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    /**
     * 获取所有订单数据
     *
     * @return
     */
    List<Order> getAllOrders(String address) throws IOException;

    /**
     * 获取某个用户的所有订单
     *
     * @param userId
     * @return
     */
    List<Order> getOrdersByUserId(String addr, Integer userId) throws IOException;

    /**
     * 更新某个订单的状态（待付款、待发货、待收货、待评价）
     */
    boolean updateStatus(String addr, Integer userId, String orderCode, Integer status) throws IOException;

    /**
     * 删除某个订单
     *
     * @param oid
     * @return
     */
    boolean deleteOrder(String addr, Integer userId, String orderCode) throws IOException;

    boolean createOrder(String addr, Order order) throws IOException;
}
