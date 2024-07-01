package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nameKazakh;

    private String nameRussian;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Transient
    private double total;

    // Getters and Setters

    @PostLoad
    @PostPersist
    @PostUpdate
    private void calculateTotal() {
        this.total = this.quantity * this.price;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameKazakh() {
        return nameKazakh;
    }

    public void setNameKazakh(String nameKazakh) {
        this.nameKazakh = nameKazakh;
    }

    public String getNameRussian() {
        return nameRussian;
    }

    public void setNameRussian(String nameRussian) {
        this.nameRussian = nameRussian;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
