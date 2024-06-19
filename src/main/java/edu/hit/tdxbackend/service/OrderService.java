package edu.hit.tdxbackend.service;

import edu.hit.tdxbackend.entity.Order;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    /**
     * 获取所有订单数据
     *
     * @return 所有订单数据
     */
    List<Order> getAllOrders(String address);

    /**
     * 获取某个用户的所有订单
     *
     * @param userId 用户id
     * @return 某个用户的所有订单
     */
    List<Order> getOrdersByUserId(String addr, Integer userId) throws IOException;

    /**
     * 更新某个订单的状态（待付款、待发货、待收货、待评价）
     */
    boolean updateStatus(String addr, Integer userId, String orderCode, Integer status) throws IOException;

    /**
     * 删除某个订单
     *
     * @param addr      地址
     * @param userId    用户id
     * @param orderCode 订单号
     * @return 是否删除成功
     * @throws IOException IO异常
     */
    boolean deleteOrder(String addr, Integer userId, String orderCode) throws IOException;

    /**
     * 创建订单
     *
     * @param addr  地址
     * @param order 订单
     * @return 是否创建成功
     * @throws IOException IO异常
     */
    boolean createOrder(String addr, Order order) throws IOException;
}
