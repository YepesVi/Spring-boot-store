package com.example.springboot.datajpa.springbootdatajpa.Models.Entity;

import com.example.springboot.datajpa.springbootdatajpa.enums.RolName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RolName rolName;
   
    public Rol() {
    }

  
    public Rol(RolName rolName) {
        this.rolName = rolName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public RolName getRolName() {
        return rolName;
    }
    public void setRolName(RolName rolName) {
        this.rolName = rolName;
    }

}
