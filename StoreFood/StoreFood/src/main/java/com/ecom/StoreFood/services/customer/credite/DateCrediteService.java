package com.ecom.StoreFood.services.customer.credite;

import com.ecom.StoreFood.dto.DateCrediteDto;
import com.ecom.StoreFood.dto.PaymentDto;
import com.ecom.StoreFood.entity.DateCredite;
import com.ecom.StoreFood.entity.Payment;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DateCrediteService {

    CompletableFuture<List<DateCredite>> listAsync();
    CompletableFuture<DateCrediteResponse> saveAsync(DateCredite dateCredite);

    CompletableFuture<DateCrediteResponse> updateAsync(int id, DateCredite payment);

    CompletableFuture<DateCrediteResponse> deleteAsync(int id);
    CompletableFuture<List<DateCredite>> listByUserIdAsync(int userId);


}
