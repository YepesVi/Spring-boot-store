package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Rol;
import com.example.springboot.datajpa.springbootdatajpa.enums.RolName;

@Repository
public interface RolRepository extends JpaRepository<Rol,Integer> {
    
    Optional<Rol> findByRolName( RolName rolName);
    boolean existsByRolName(RolName rolName);

}
