package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Cliente;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ClienteDaoImp implements IClienteDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAll() {
        return em.createQuery("from Cliente").getResultList();
    }

    @Transactional
    @Override
    public void Save(Cliente cliente) {
        em.persist(cliente);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            em.remove(cliente);
        }
    }

    @Transactional
    @Override
    public void update(Cliente clienteEditado) {
        Cliente cliente = em.find(Cliente.class, clienteEditado.getId());
        if (cliente != null) {
            // Modificar los campos necesarios de la entidad
            clienteEditado.setCreateAt(cliente.getCreateAt());
            // Guardar la entidad actualizada en la base de datos
            em.merge(clienteEditado);
        }

    }

    @Transactional
    @Override
    public Cliente search(Long id) {
        return em.find(Cliente.class, id);
    }

   @Transactional
    @Override
    public Optional<Cliente> findByEmail(String email) {
        String jpql = "SELECT c FROM Cliente c WHERE c.email = :email";
        TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
        query.setParameter("email", email);
        List<Cliente> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEmail(String email) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.email = :email", Long.class);
        query.setParameter("email", email);
        Long count = query.getSingleResult();
        return count > 0;
    }

   
        

        
}

   


