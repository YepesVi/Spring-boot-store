package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;

import java.util.List;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Compra;

public interface ICompraDao {

    public List<Compra> findAll();

    public void Save(Compra compra);

    public Compra search(Long id);

    public void delete(Compra compra);

    public void update(Compra compra);
    
}
