package com.ecom.StoreFood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditResultsDto {
    private double van;
    private double tir;
    private double tcea;
    private double mountFinal;
}
