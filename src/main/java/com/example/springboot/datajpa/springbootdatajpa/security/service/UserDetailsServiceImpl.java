package com.example.springboot.datajpa.springbootdatajpa.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.ClienteRepository;
import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.IClienteDao;
import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Cliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{



    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private IClienteDao clienteDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Attempting to load user by email: {}", email);
        Cliente cliente = clienteDao.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("User not found with email: {}", email);
                    return new UsernameNotFoundException("User Not Found with email: " + email);
                });
        logger.info("User found: {}", cliente);
        return UsuarioPrincipal.build(cliente);
    }


    
}
