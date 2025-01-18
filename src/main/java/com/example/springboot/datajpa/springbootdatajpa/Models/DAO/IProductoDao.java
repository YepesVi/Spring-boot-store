package com.example.springboot.datajpa.springbootdatajpa.Models.DAO;

import java.util.List;



import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Producto;

public interface IProductoDao {
    
    public List<Producto> findAll();

    public void Save(Producto producto);
    
    public void delete(Long id);
    
    public void update(Producto producto);

    public Producto search(Long id);

    public void subStock(Long id, int x);

}
