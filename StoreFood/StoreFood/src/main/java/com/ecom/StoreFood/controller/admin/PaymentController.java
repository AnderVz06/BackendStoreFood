package com.ecom.StoreFood.controller.admin;

import com.ecom.StoreFood.dto.PaymentDto;
import com.ecom.StoreFood.entity.Payment;
import com.ecom.StoreFood.services.customer.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/credit/{creditId}")
    public ResponseEntity<Payment> addPayment(@PathVariable Long creditId, @RequestBody PaymentDto paymentDto) {
        Payment newPayment = paymentService.addPayment(creditId, paymentDto);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }

    @GetMapping("/credit/{creditId}")
    public ResponseEntity<List<Payment>> getPaymentsByCredit(@PathVariable Long creditId) {
        List<Payment> payments = paymentService.getPaymentsByCredit(creditId);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}
