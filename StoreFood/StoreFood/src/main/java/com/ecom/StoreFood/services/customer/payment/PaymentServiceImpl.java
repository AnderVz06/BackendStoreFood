package com.ecom.StoreFood.services.customer.payment;

import com.ecom.StoreFood.dto.PaymentDto;
import com.ecom.StoreFood.entity.DateCredite;
import com.ecom.StoreFood.entity.Payment;
import com.ecom.StoreFood.repository.DateCrediteRepository;
import com.ecom.StoreFood.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final DateCrediteRepository dateCrediteRepository;



    @Override
    public Payment addPayment(Long creditId, PaymentDto paymentDto) {
        return null;
    }

    public List<Payment> getPaymentsByCredit(Long creditId) {
        return paymentRepository.findByDateCrediteId(creditId);
    }
}
