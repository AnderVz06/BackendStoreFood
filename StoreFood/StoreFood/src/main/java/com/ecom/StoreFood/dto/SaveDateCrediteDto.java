package com.ecom.StoreFood.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class SaveDateCrediteDto {

    private int id;

    private String tipoMoneda;  // tipo de moneda
    private String tipoTasaInteres; // tipo de tasa (nominal o efectiva)
    private String tipoCapitalizacion; // tipo de capitalizacion (c.m, c.B)
    private Double tasaAnual;  // valor de la tasa de interes anual
    private int cuotas;
    private int tiempoCredito; // tiempo de credito
    private Double pagoInicial; // monto de pago inicial
    private Double montoFinanciar;
    private Boolean credito;
    private Double tasaMoratoria; // tasa de interés moratoria
    private Double limiteCredito; // límite de crédito
    private int fechaPagoMensual; // fecha de pago mensual
    private Boolean conPlazoGracia;
    private String tipoPlazoGracia;
    private Long tiempoPlazoGracia;
    private Long mesesInicio;
    private int fechaFinalMes;






}
