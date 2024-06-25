package com.ecom.StoreFood.services.customer.credite;

import com.ecom.StoreFood.entity.DateCredite;




public class DateCrediteResponse extends BaseResponse<DateCredite>{

    private boolean success;
    private String message;
    private DateCredite payment;


    public DateCrediteResponse(String message) {
        super(message);
    }

    public DateCrediteResponse(DateCredite resource) {
        super(resource);
    }


    public DateCrediteResponse(boolean success, String message, DateCredite dateCredite) {
        super(success, message, dateCredite);
    }


    public Object getDateCredite() {
        return this.payment;
    }
}
