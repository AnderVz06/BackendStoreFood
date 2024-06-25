package com.ecom.StoreFood.repository;


import com.ecom.StoreFood.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByDateCrediteId(Long creditId);

}
