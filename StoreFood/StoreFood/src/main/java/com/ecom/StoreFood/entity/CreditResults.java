package com.ecom.StoreFood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@Table(name = "results")
@AllArgsConstructor
@NoArgsConstructor
public class CreditResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double van;
    private double tir;
    private double tcea;

    private double mountFinal;

    @ManyToOne
    @JoinColumn(name = "credit_id", nullable = false)
    private DateCredite dateCredite;
}
