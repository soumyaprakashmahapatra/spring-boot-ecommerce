package com.ecommerce.sb_ecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @NotBlank
    @Size(min=3 , message = "Product name must contain atleast 3 characters")
    private String productName;//atleast 3 character
    @NotBlank
    @Size(min=6 , message = "Product name must contain atleast 6 characters")
    private String description; //Atleast 6 character
    private String image;
    private Integer quantity;
    private double price;
    private double discount;
    private Double specialPrice;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}




