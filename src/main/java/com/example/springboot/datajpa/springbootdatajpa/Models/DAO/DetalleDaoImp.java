package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Compra;
import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Detalle;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class DetalleDaoImp implements IDetalleDao{

    @PersistenceContext
    EntityManager em;


    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Detalle> findAll() {
        return em.createQuery("From Detalle").getResultList();
    }

    @Transactional
    @Override
    public Detalle search(Long id) {
        return em.find(Detalle.class, id);
    }

    @Transactional
    @Override
    public void Save(Detalle detalle) {
        
        em.persist(detalle);
    }

    @Transactional
    @Override
    public void update(Detalle detalleEditado) {
        Detalle detalle = em.find(Detalle.class, detalleEditado.getId());
        if (detalle != null) {
            
            em.merge(detalleEditado);
        }

    }

    @Transactional
    @Override
    public void delete(Detalle detalle) {
        Detalle detalle2 = em.find(Detalle.class, detalle.getId());
        if (detalle2 != null) {
            em.remove(detalle2);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Detalle> find(Compra compra) {
        String jpql = "SELECT d FROM Detalle d WHERE d.compra = :compra";
        TypedQuery<Detalle> query = em.createQuery(jpql, Detalle.class);
        query.setParameter("compra", compra);
        return query.getResultList();
    }
    
    @Transactional
    public float valorDes(Compra compra)
    {
        float sum=0;
        List<Detalle> list= find(compra);
        Iterator<Detalle> iterador=list.iterator();
        Detalle det= new Detalle();
        while (iterador.hasNext()) {
            det=iterador.next();
            sum+=det.getValor();
        }
        return sum ;
    }


    @Transactional
    public float valorTot(Compra compra)
    {
        float sum=0;
        List<Detalle> list= find(compra);
        Iterator<Detalle> iterador=list.iterator();
        Detalle det= new Detalle();
        while (iterador.hasNext()) {
            det=iterador.next();
            sum+=det.getCantidad()*det.getProducto().getValorUnidad();
        }
        return sum ;
    }
    
    @Transactional
    public float DescTot(Compra compra)
    {
        float sum=0;
        List<Detalle> list= find(compra);
        Iterator<Detalle> iterador=list.iterator();
        Detalle det= new Detalle();
        while (iterador.hasNext()) {
            det=iterador.next();
            sum+=det.getCantidad()*det.getProducto().getValorUnidad()*det.getDescuento();
        }
        return sum ;
    }

}
