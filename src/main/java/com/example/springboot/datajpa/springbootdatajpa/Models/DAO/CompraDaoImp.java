package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Compra;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class CompraDaoImp implements ICompraDao{

    @PersistenceContext
    private EntityManager em;


    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Compra> findAll() {
        return em.createQuery("from Compra").getResultList();
    }

    @Transactional
    @Override
    public void Save(Compra compra) {
        em.persist(compra);
    }

    @Transactional
    @Override
    public Compra search(Long id) {
        return em.find(Compra.class, id);
    }
    

    @Transactional
    @Override
    public void delete(Compra compra) {
        Compra compra2 = em.find(Compra.class, compra.getId());
        if (compra2 != null) {
            em.remove(compra2);
        }
    }

    @Transactional
    @Override
    public void update(Compra compraEditada) {
        Compra compra = em.find(Compra.class, compraEditada.getId());
        if (compra != null) {
            // Modificar los campos necesarios de la entidad
            compraEditada.setCreateAt(compra.getCreateAt());
            // Guardar la entidad actualizada en la base de datos
            em.merge(compraEditada);
        }

    }
    
}
