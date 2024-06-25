package com.ecom.StoreFood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class AddProductInCartDto {


  private Long userId;
  private Long productId;

}
