package com.ecom.StoreFood.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {
    private Long id;

    private String name;

    private Long price;


    private String description;

    private String categoryName;

    private byte[] byteImg;

    private Long categoryId;
    private MultipartFile img;

}
