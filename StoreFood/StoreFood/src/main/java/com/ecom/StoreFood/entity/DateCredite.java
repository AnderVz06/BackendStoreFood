package com.ecom.StoreFood.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credites")
public class DateCredite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "dateCredite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();
}