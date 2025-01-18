/*package com.example.springboot.datajpa.springbootdatajpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.RolService;
import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Rol;
import com.example.springboot.datajpa.springbootdatajpa.enums.RolName;

@Service
public class CreateRoles implements CommandLineRunner{

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {

        Rol rolAdmin = new Rol(RolName.Admin);
        Rol rolAdminCliente= new Rol(RolName.Admin_cliente);
        Rol rolAdminProducto = new Rol(RolName.Admin_producto);
        Rol rolCliente = new Rol(RolName.Cliente);

        rolService.save(rolAdmin);
        rolService.save(rolAdminCliente);
        rolService.save(rolAdminProducto);
        rolService.save(rolCliente);


    }
    
}
*/