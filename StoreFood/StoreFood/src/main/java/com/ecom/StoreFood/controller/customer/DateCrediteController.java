package com.ecom.StoreFood.controller.customer;

import com.ecom.StoreFood.dto.DateCrediteDto;

import com.ecom.StoreFood.dto.SaveDateCrediteDto;

import com.ecom.StoreFood.entity.DateCredite;

import com.ecom.StoreFood.services.customer.credite.DateCrediteResponse;
import com.ecom.StoreFood.services.customer.credite.DateCrediteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class DateCrediteController {


    private final DateCrediteService dateCrediteService;


    private final ModelMapper modelMapper;





    @GetMapping("/credit")
    public ResponseEntity<List<DateCrediteDto>> getAllDateCredit() {
        List<DateCredite> payments = dateCrediteService.listAsync().join(); // Esperamos la lista de pagos de forma síncrona
        List<DateCrediteDto> paymentResources = payments.stream()
                .map(payment -> modelMapper.map(payment, DateCrediteDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentResources);
    }

    @PostMapping("/create/credit")
    public ResponseEntity<?> createPayment(@Valid @RequestBody SaveDateCrediteDto resource) {
        if (!isValidResource(resource)) {
            return ResponseEntity.badRequest().body("Invalid resource provided");
        }

        DateCredite dateCredite = modelMapper.map(resource, DateCredite.class);
        CompletableFuture<DateCrediteResponse> saveAsync = dateCrediteService.saveAsync(dateCredite);

        try {
            DateCrediteResponse response = saveAsync.join(); // Espera el resultado de la operación asíncrona
            if (!response.isSuccess()) {
                return ResponseEntity.badRequest().body(response.getMessage());
            }

            // Aquí accedemos correctamente al objeto DateCredite desde DateCrediteResponse
            DateCrediteDto paymentResource = modelMapper.map(response.getDateCredite(), DateCrediteDto.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentResource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable("id") int id, @Valid @RequestBody SaveDateCrediteDto resource) {
        if (!isValidResource(resource)) {
            return ResponseEntity.badRequest().body("Invalid resource provided");
        }

        DateCredite payment = modelMapper.map(resource, DateCredite.class);
        DateCredite updatedPayment = dateCrediteService.updateAsync(id, payment).join().getResource(); // Esperamos la actualización del pago de forma síncrona
        if (updatedPayment == null) {
            return ResponseEntity.notFound().build();
        }

        DateCrediteDto paymentResource = modelMapper.map(updatedPayment, DateCrediteDto.class);
        return ResponseEntity.ok(paymentResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable("id") int id) {
        boolean deleted = dateCrediteService.deleteAsync(id).join().isSuccess(); // Esperamos la eliminación del pago de forma síncrona
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


    private boolean isValidResource(SaveDateCrediteDto resource) {
        if (resource.getCurrency() == null || resource.getCurrency().isEmpty()) {
            return false;
        }
        if (resource.getSelectedRate() == null || resource.getSelectedRate().isEmpty()) {
            return false;
        }
        if (resource.getSelectedPeriod() == null || resource.getSelectedPeriod().isEmpty()) {
            return false;
        }
        if (resource.getRateType() == null || resource.getRateType().isEmpty()) {
            return false;
        }
        if (resource.getRateValue() <= 0) {
            return false;
        }
        if (resource.getClosingDate() <= 0) {
            return false;
        }
        if (resource.getTotalGracePeriod() < 0) {
            return false;
        }
        if (resource.getPartialGracePeriod() < 0) {
            return false;
        }
        if (resource.getInitialFee() < 0) {
            return false;
        }
        if (resource.getFinalFee() < 0) {
            return false;
        }
        if (resource.getCreditLifeInsurance() < 0) {
            return false;
        }
        if (resource.getFormattedRateValue() <= 0) {
            return false;
        }
        if (resource.getCok() < 0) {
            return false;
        }
        if (resource.getVan() < 0) {
            return false;
        }
        if (resource.getTir() < 0) {
            return false;
        }
        if (resource.getTcea() < 0) {
            return false;
        }
        if (resource.getTotalPrice() <= 0) {
            return false;
        }
        return true;
    }

}
