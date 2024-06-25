package com.ecom.StoreFood.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignupRequest {
    private  String email;
    private String password;
    private String name;

}
