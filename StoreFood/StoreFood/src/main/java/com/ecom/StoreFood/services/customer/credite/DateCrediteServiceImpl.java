package com.ecom.StoreFood.services.customer.credite;


import com.ecom.StoreFood.entity.*;

import com.ecom.StoreFood.repository.DateCrediteRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DateCrediteServiceImpl implements DateCrediteService {



    @Autowired
    private DateCrediteRepository  dateCrediteRepository;

    public CompletableFuture<List<DateCredite>> listAsync() {
        return CompletableFuture.supplyAsync(() -> dateCrediteRepository.findAll());
    }

    public CompletableFuture<DateCrediteResponse> saveAsync(DateCredite dateCredite) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                DateCredite savedDateCredite = dateCrediteRepository.save(dateCredite);
                return new DateCrediteResponse(true, "Payment saved successfully", savedDateCredite);
            } catch (Exception e) {
                return new DateCrediteResponse(false, "Error saving payment: " + e.getMessage(), null);
            }
        });
    }

    public CompletableFuture<DateCrediteResponse> updateAsync(int id, DateCredite payment) {
        return CompletableFuture.supplyAsync(() -> {
            return dateCrediteRepository.findById(id).map(existingPayment -> {
                existingPayment.setTipoMoneda(payment.getTipoMoneda());
                existingPayment.setTipoTasaInteres(payment.getTipoTasaInteres());
                existingPayment.setTipoCapitalizacion(payment.getTipoCapitalizacion());
                existingPayment.setTasaAnual(payment.getTasaAnual());
                existingPayment.setCuotas(payment.getCuotas());
                existingPayment.setTiempoCredito(payment.getTiempoCredito());
                existingPayment.setPagoInicial(payment.getPagoInicial());
                existingPayment.setMontoFinanciar(payment.getMontoFinanciar());
                existingPayment.setCredito(payment.getCredito());
                existingPayment.setTasaMoratoria(payment.getTasaMoratoria());
                existingPayment.setLimiteCredito(payment.getLimiteCredito());
                existingPayment.setFechaPagoMensual(payment.getFechaPagoMensual());
                existingPayment.setConPlazoGracia(payment.getConPlazoGracia());
                existingPayment.setTipoPlazoGracia(payment.getTipoPlazoGracia());
                existingPayment.setTiempoPlazoGracia(payment.getTiempoPlazoGracia());
                existingPayment.setMesesInicio(payment.getMesesInicio());
                existingPayment.setFechaFinalMes(payment.getFechaFinalMes());

                try {
                    dateCrediteRepository.save(existingPayment);
                    return new DateCrediteResponse(true, "Payment updated successfully", existingPayment);
                } catch (Exception e) {
                    return new DateCrediteResponse("An error occurred while updating the payment: " + e.getMessage());
                }
            }).orElseGet(() -> new DateCrediteResponse("Payment not found."));
        });
    }

    public CompletableFuture<DateCrediteResponse> deleteAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            return  dateCrediteRepository.findById(id).map(dateCredite -> {
                dateCrediteRepository.delete(dateCredite);
                return new DateCrediteResponse(true, "Payment deleted successfully", dateCredite);
            }).orElseGet(() -> new DateCrediteResponse(false, "Payment not found", null));
        });
    }

    public CompletableFuture<List<DateCredite>> listByUserIdAsync(int userId) {
        return CompletableFuture.supplyAsync(() -> (List<DateCredite>) dateCrediteRepository.findAllByUserId(userId));
    }


}
