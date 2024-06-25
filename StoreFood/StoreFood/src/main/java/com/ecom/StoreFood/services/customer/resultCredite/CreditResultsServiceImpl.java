package com.ecom.StoreFood.services.customer.resultCredite;

import com.ecom.StoreFood.dto.CreditResultsDto;
import com.ecom.StoreFood.entity.CreditResults;
import com.ecom.StoreFood.repository.CreditResultsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditResultsServiceImpl implements CrediteResultsService {


    private final CreditResultsRepository creditResultsRepository;

    public CreditResultsDto getCreditResultsByCreditId(Long creditId) {
        CreditResults creditResults = creditResultsRepository.findByDateCrediteId(creditId)
                .orElseThrow(() -> new IllegalArgumentException("Resultados no encontrados para el crédito ID: " + creditId));

        return new CreditResultsDto(
                creditResults.getVan(),
                creditResults.getTir(),
                creditResults.getTcea(),
                creditResults.getMountFinal()
        );
    }
}
