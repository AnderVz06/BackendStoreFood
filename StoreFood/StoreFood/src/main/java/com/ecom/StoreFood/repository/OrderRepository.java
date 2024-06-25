package com.ecom.StoreFood.repository;

import com.ecom.StoreFood.entity.Order;
import com.ecom.StoreFood.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

}
