package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Rol;
import com.example.springboot.datajpa.springbootdatajpa.enums.RolName;



@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public void save(Rol rol){
        rolRepository.save(rol);
    }

    public List<Rol> findAll(){
        return rolRepository.findAll();
    }
    
    public Optional<Rol> getByRolNombre(RolName rolName){
        return rolRepository.findByRolName(rolName);
    }

    public boolean exisexistsByRolName(RolName rolName){
        return rolRepository.existsByRolName(rolName);
    }
}
