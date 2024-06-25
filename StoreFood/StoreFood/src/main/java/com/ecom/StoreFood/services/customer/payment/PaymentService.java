package com.ecom.StoreFood.services.customer.payment;

import com.ecom.StoreFood.dto.PaymentDto;
import com.ecom.StoreFood.entity.Payment;

import java.util.List;

public interface PaymentService {

    Payment addPayment(Long creditId, PaymentDto paymentDto);
    List<Payment> getPaymentsByCredit(Long creditId);
}
