package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;

import java.util.List;
import java.util.Optional;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Cliente;


public interface IClienteDao {

    public List<Cliente> findAll();

    public void Save(Cliente cliente);

    public void delete(Long id);
    
    public void update(Cliente cliente);

    public Cliente search(Long id);

    public Optional<Cliente> findByEmail(String email);

    public boolean existsByEmail(String email);

    
}
