package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;

import java.util.List;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Compra;
import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Detalle;

public interface IDetalleDao {

    public List<Detalle> findAll();

    public List<Detalle> find(Compra compra);
    
    public Detalle search(Long id);

    public void Save(Detalle detalle);

    public void update(Detalle detalle);

    public void delete(Detalle detalle);
    
}
