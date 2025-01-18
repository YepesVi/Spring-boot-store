package com.example.springboot.datajpa.springbootdatajpa.Models.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


@Entity
public class Detalle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    @ManyToOne
    @JoinColumn(name="compra_id")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;

    @Positive
    private int cantidad;

    @Positive
    private float valor;

    @PositiveOrZero
    private float descuento;

  

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
