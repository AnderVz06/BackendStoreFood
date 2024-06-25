package com.ecom.StoreFood.dto;

import com.ecom.StoreFood.entity.DateCredite;
import lombok.Data;


import java.time.LocalDate;

@Data
public class PaymentDto {


    private Double amount;
    private LocalDate paymentDate;


    private DateCredite dateCredite;
}
