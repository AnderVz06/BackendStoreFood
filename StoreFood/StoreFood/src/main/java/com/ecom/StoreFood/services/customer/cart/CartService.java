package com.ecom.StoreFood.services.customer.cart;

import com.ecom.StoreFood.dto.AddProductInCartDto;
import com.ecom.StoreFood.dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
    OrderDto getCartByUserId(Long userId);




}
