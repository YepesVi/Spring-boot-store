package com.example.springboot.datajpa.springbootdatajpa.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.IClienteDao;
import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.RolService;
import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Cliente;
import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Rol;
import com.example.springboot.datajpa.springbootdatajpa.enums.RolName;

@Service
public class CreateAdmin  implements CommandLineRunner{
    
    @Autowired
    IClienteDao clienteDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        /* 
        Rol rol_Admin = new Rol(RolName.ROLE_ADMIN);
        Rol rol_AdminCliente= new Rol(RolName.ROLE_Admin_cliente);
        Rol rol_AdminProducto = new Rol(RolName.ROLE_Admin_producto);
        Rol rol_Cliente = new Rol(RolName.ROLE_Cliente);

        rolService.save(rol_Admin);
        rolService.save(rol_AdminCliente);
        rolService.save(rol_AdminProducto);
        rolService.save(rol_Cliente);
        
        Cliente cliente = new Cliente();
        
        String passwordEncoded = passwordEncoder.encode("admin");
        cliente.setNombre("admin");
        cliente.setApellido("adminsiando");
        cliente.setEmail("admin@gmail.com");
        cliente.setClave(passwordEncoded);
        Rol rolAdmin = rolService.getByRolNombre(RolName.ROLE_ADMIN).get();
        Rol rolAdminClientes = rolService.getByRolNombre(RolName.ROLE_Admin_cliente).get();
        Rol rolAdminProductos = rolService.getByRolNombre(RolName.ROLE_Admin_producto).get();
        Rol rolCliente = rolService.getByRolNombre(RolName.ROLE_Cliente).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rolAdmin);
        roles.add(rolAdminClientes);
        roles.add(rolAdminProductos);
        roles.add(rolCliente);
        cliente.setRoles(roles);
        cliente.setStatus(true);
        clienteDao.Save(cliente);
        

*/

    }
}
