package com.ecom.StoreFood.services.customer;

import com.ecom.StoreFood.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {
 List<ProductDto> getAllProducts();
 List<ProductDto> searchProductByTitle(String name);
}
