package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente,Long>{
    Optional<Cliente> findByEmail( String email);
    boolean existsByEmail(String email);
}
