package edu.hit.tdxbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderCode;
    private int productCount;
    private Double totalPrice;
    private String createDate;
    private String payDate;
    private String deliveryDate;
    private String confirmDate;
    private Integer uid;
    private Integer pid;
    private Integer count;
    private Integer status;
    private ProductImage productImage;
}
