package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class ProductoDaoImp implements IProductoDao{

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Producto> findAll() {
        return em.createQuery("from Producto").getResultList();
    }

    @Transactional
    @Override
    public void Save(Producto producto) {
       em.persist(producto);
    
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Producto producto = em.find(Producto.class, id);
        if (producto != null) {
            em.remove(producto);
        }
    }

    @Transactional
    @Override
    public void update(Producto productoEditado) {
        Producto producto = em.find(Producto.class, productoEditado.getId());
        if (producto != null) {
           
            em.merge(productoEditado);
        }

    }
    
    @Transactional
    @Override
    public Producto search(Long id) {
        return em.find(Producto.class, id);
    }

    @Transactional
    @Override
    public void subStock(Long id, int x) {
        Producto producto = search(id);
        if(producto!=null)
        {
            producto.setStock(producto.getStock()-x);
            em.merge(producto);
        }

    }
}
