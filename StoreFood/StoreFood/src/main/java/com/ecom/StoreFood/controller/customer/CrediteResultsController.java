package com.ecom.StoreFood.controller.customer;

import com.ecom.StoreFood.dto.CreditResultsDto;
import com.ecom.StoreFood.services.customer.resultCredite.CrediteResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class CrediteResultsController {



    private final CrediteResultsService creditResultsService;

    @GetMapping("/{creditId}")
    public ResponseEntity<CreditResultsDto> getCreditResults(@PathVariable Long creditId) {
        CreditResultsDto creditResultsDto = creditResultsService.getCreditResultsByCreditId(creditId);
        return ResponseEntity.ok(creditResultsDto);
    }





}
