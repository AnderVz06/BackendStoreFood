package com.ecom.StoreFood.services.customer.resultCredite;

import com.ecom.StoreFood.dto.CreditResultsDto;
import com.ecom.StoreFood.dto.DateCrediteDto;

public interface CrediteResultsService {

    CreditResultsDto getCreditResultsByCreditId(Long creditId);
}
