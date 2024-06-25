package com.ecom.StoreFood.dto;

import com.ecom.StoreFood.entity.CartItems;
import com.ecom.StoreFood.entity.User;
import com.ecom.StoreFood.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {

    private Long id;
    private String orderDescription;
    private Date date;
    private Long amount;
    private String address;
    private String payment;
    private OrderStatus orderStatus;
    private Long totalAmount;
    private Long discount;
    private UUID trackingId;


    private String  userName;


    private List<CartItemsDto> cartItems;


}
