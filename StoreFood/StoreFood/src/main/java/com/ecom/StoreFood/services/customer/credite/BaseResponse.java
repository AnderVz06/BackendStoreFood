package com.ecom.StoreFood.services.customer.credite;


import lombok.Data;

@Data
public abstract class BaseResponse<T> {

    private boolean success;
    private String message;
    private T resource;

    protected BaseResponse(String message) {
        this.success = false;
        this.message = message;
        this.resource = null;
    }

    protected BaseResponse(T resource) {
        this.success = true;
        this.message = "";
        this.resource = resource;
    }

    public BaseResponse(boolean success, String message, T resource) {
        this.success = success;
        this.message = message;
        this.resource = resource;
    }
}
