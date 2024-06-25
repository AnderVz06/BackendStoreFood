package com.ecom.StoreFood.controller.customer;

import com.ecom.StoreFood.dto.AddProductInCartDto;
import com.ecom.StoreFood.dto.CartItemsDto;
import com.ecom.StoreFood.dto.OrderDto;
import com.ecom.StoreFood.entity.CartItems;
import com.ecom.StoreFood.services.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto){
       return cartService.addProductToCart(addProductInCartDto);
    }



    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
        OrderDto orderDto = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }


}
