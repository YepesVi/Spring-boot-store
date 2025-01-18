package com.example.springboot.datajpa.springbootdatajpa.Models.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    
    @PositiveOrZero
    private float Valor, subTotal, descuento;

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date CreateAt;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Detalle> productos;

    

    @PrePersist
    public void perPersist(){

        CreateAt= new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float valor) {
        Valor = valor;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Detalle> getProductos() {
        return productos;
    }

    public void setProductos(List<Detalle> productos) {
        this.productos = productos;
    }

    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;


    
}
