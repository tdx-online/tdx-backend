package edu.hit.tdxbackend.controller;

import edu.hit.tdxbackend.entity.Order;
import edu.hit.tdxbackend.entity.ResultInfo;
import edu.hit.tdxbackend.entity.User;
import edu.hit.tdxbackend.mapper.ProductMapper;
import edu.hit.tdxbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/order")
@SessionAttributes("user")
public class OrderController {
    private final OrderService orderService;
    private final ProductMapper productMapper;

    @Autowired
    public OrderController(OrderService orderService, ProductMapper productMapper) {
        this.orderService = orderService;
        this.productMapper = productMapper;
    }

    @GetMapping("/getAllOrders")
    public ResultInfo getAllOrders(@SessionAttribute(name = "user", required = false) User user) throws IOException {
        ResultInfo info = new ResultInfo();
        if (user == null) {
            info.setFlag(false);
            info.setErrorMsg("用户未登录");
            return info;
        }
        String address = user.getBlockAddress();
        List<Order> orders = orderService.getAllOrders(address);
        if (orders == null) {
            info.setFlag(false);
            info.setErrorMsg("获取订单失败");
        } else {
            info.setFlag(true);
            info.setData(orders);
        }
        return info;
    }

    @GetMapping("/getOrdersByUserId")
    public ResultInfo getOrdersByUserId(@SessionAttribute(name = "user", required = false) User user, @RequestParam("uid") Integer userId) throws IOException {
        ResultInfo info = new ResultInfo();
        if (user == null || userId == null) {
            info.setFlag(false);
            info.setErrorMsg("用户未登录或用户ID无效");
            return info;
        }
        String addr = user.getBlockAddress();
        List<Order> orders = orderService.getOrdersByUserId(addr, userId);
        if (orders == null) {
            info.setFlag(false);
            info.setErrorMsg("获取订单失败");
        } else {
            info.setFlag(true);
            info.setData(orders);
        }
        if (orders != null) {
            for (Order order : orders) {
                order.setProductImage(productMapper.getOneImageByProductId(order.getPid()));
            }
        }
        return info;
    }

    @PostMapping("/updateStatus")
    public ResultInfo updateStatus(@SessionAttribute(name = "user", required = false) User user, @RequestParam("id") String orderCode, @RequestParam("uid") Integer userId, @RequestParam("status") int status) throws IOException {
        ResultInfo info = new ResultInfo();
        if (user == null) {
            info.setFlag(false);
            info.setErrorMsg("用户未登录");
            return info;
        }
        String addr = user.getBlockAddress();
        boolean flag = orderService.updateStatus(addr, userId, orderCode, status);
        if (flag) {
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("更新订单失败");
        }
        return info;
    }

    @PostMapping("/deleteOrder")
    public ResultInfo deleteOrder(@SessionAttribute(name = "user", required = false) User user, @RequestBody Integer userId, @RequestBody String orderCode) throws IOException {
        ResultInfo info = new ResultInfo();
        if (user == null) {
            info.setFlag(false);
            info.setErrorMsg("用户未登录");
            return info;
        }
        String addr = user.getBlockAddress();
        boolean flag = orderService.deleteOrder(addr, userId, orderCode);
        if (flag) {
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("删除订单失败");
        }
        return info;
    }

    @PostMapping("/createOrder")
    public ResultInfo createOrder(@SessionAttribute(name = "user", required = false) User user, @RequestBody Order order) throws IOException {
        ResultInfo info = new ResultInfo();
        if (user == null) {
            info.setFlag(false);
            info.setErrorMsg("用户未登录");
            return info;
        }
        String orderCode = generateOrderCode();
        String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int status = 0;

        order.setOrderCode(orderCode);
        order.setCreateDate(createDate);
        order.setStatus(status);
        String addr = user.getBlockAddress();
        boolean flag = orderService.createOrder(addr, order);
        if (flag) {
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("创建订单失败");
        }
        return info;
    }

    private static String generateOrderCode() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String datePrefix = dtf.format(LocalDateTime.now());

        Random rand = new Random();
        int randomSuffix = 1000 + rand.nextInt(9000);

        return datePrefix + randomSuffix;
    }
}
