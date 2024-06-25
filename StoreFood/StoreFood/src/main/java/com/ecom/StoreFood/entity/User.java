package com.ecom.StoreFood.entity;

import com.ecom.StoreFood.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data

@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    private String name;

    private UserRole role;

    @Lob // mapea grandes volumnes de datos a la base de datos
    @Column(columnDefinition = "longblob")
    private byte[] img;


    private Double limiteCredito; // Límite de crédito del cliente


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DateCredite> credits = new ArrayList<>();



}
