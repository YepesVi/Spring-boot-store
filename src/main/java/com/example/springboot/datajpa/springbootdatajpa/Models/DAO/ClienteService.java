package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Cliente;


@Service
@Transactional
public class ClienteService {

    @Autowired
    ClienteRepository  clienteRepository;  
    
    public List<Cliente> lista(){
        return clienteRepository.findAll();
    }

    @SuppressWarnings("deprecation")
    public Cliente getById(Long id){
        return clienteRepository.getById(id);
    }

    public Optional<Cliente> getByEmail(String email){
        return clienteRepository.findByEmail(email);
    }


    public boolean existsByEmail(String email){
        return clienteRepository.existsByEmail(email);
    }
}
     