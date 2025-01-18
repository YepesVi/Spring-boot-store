package com.example.springboot.datajpa.springbootdatajpa.Models.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class Producto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @NotEmpty
    @Size(min=4, max=15)
    private String Nombre;

    @NotEmpty
    private String Descripcion;

    //@NotNull //valida que la fecha no sea nula
    @Column(name = "valor_unidad")
    @PositiveOrZero
    private float ValorUnidad;

    @PositiveOrZero
    private long stock;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public float getValorUnidad() {
        return ValorUnidad;
    }

    public void setValorUnidad(float valorUnidad) {
        ValorUnidad = valorUnidad;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;


    
}
