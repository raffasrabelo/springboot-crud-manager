package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser um valor positivo")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "A quantidade em estoque é obrigatória")
    @Positive(message = "O estoque deve ser um número positivo")
    @Column(name = "quantidade_estoque")
    private Integer stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id")
    @NotNull(message = "É obrigatório selecionar um fornecedor")
    private Supplier supplier;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}