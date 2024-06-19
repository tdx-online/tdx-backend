package edu.hit.tdxbackend.mapper;

import edu.hit.tdxbackend.entity.Order;
import edu.hit.tdxbackend.entity.OrderItem;
import edu.hit.tdxbackend.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 获取所有订单
     * @return 所有订单
     */
    List<Order> getAllOrders();

    /**
     * 获取用户的所有订单
     * @param userId 用户id
     * @return 用户的所有订单
     */
    List<Order> getOrdersByUserId(@Param("userId") String userId);

    /**
     * 根据订单id获取订单商品
     * @param orderId 订单id
     * @return 订单商品
     */
    List<OrderItem> getOrderItemsByOrderId(int orderId);

    /**
     * 更新某个订单的状态（0待付款、1待发货、2待收货、3待评价）
     * @param oid 订单id
     * @param status 订单状态
     * @return 是否更新成功
     */
    boolean updateStatus(@Param("oid") int oid, @Param("status") int status);

    /**
     * 删除某个订单
     * @param oid 订单id
     * @return 是否删除成功
     */
    boolean deleteOrder(int oid);

    /**
     * 创建订单
     * @param order 订单
     * @return 是否创建成功
     */
    boolean createOrder(Order order);

    /**
     * 创建订单商品
     * @param oid 订单id
     * @param orderItems 订单商品
     * @return 是否创建成功
     */
    boolean createOrderItems(@Param("oid") int oid, @Param("orderItems") List<OrderItem> orderItems);
}
