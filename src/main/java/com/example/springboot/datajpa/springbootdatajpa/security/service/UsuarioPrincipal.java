package com.example.springboot.datajpa.springbootdatajpa.security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Cliente;



public class UsuarioPrincipal implements UserDetails{

   private Long Id;

    private String nombre;

    
    private String apellido;

    
    private String email;


    private String clave;

    private Boolean status;

    private Collection<? extends GrantedAuthority> authorities;

    


    public UsuarioPrincipal(Long id, String nombre, String apellido, String email, String clave, Boolean status,
            Collection<? extends GrantedAuthority> authorities) {
        Id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.clave = clave;
        this.status = status;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal build(Cliente cliente){

        List<GrantedAuthority> authorities =
                cliente.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolName().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail(), cliente.getClave(), cliente.getStatus(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return clave;   
    }

    @Override
    public String getUsername() {
       return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
       return true;
    }

    public Long getId() {
        return Id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }


    public Boolean getStatus() {
        return status;
    }
    
    
}
