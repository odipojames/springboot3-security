package com.ey.springboot3security.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message="name must not be provided")
    @NotNull(message="name must not be null")
    private String name;
    
    @Positive(message="a correct price must not be provided")
    private float price;

   
    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private UserInfo user; // Define the many-to-one relationship



    
}