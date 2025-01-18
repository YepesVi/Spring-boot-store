package com.example.springboot.datajpa.springbootdatajpa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.IProductoDao;

import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Producto;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;







@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private IProductoDao productoDao;

    
    @GetMapping("/listar")
    public String Listar(Model model) {
        model.addAttribute("encab", "Productos");
        model.addAttribute("titulo","Listado de Productos");

        model.addAttribute("producto", productoDao.findAll());
        return "productos/listar";
    }

     @GetMapping("/form")
    public String crear(Model model) {

        Producto producto = new Producto();
        model.addAttribute("titulo", "Formulario de producto");
        model.addAttribute("producto", producto);
        return "productos/form";
    }


    @PostMapping("/form") //para validar se  agrega el valid y el bindingResul, estos siempre deben estar juntos uno tras otro
    public String Guardar(@Valid Producto producto, BindingResult result, Model model)
    {

        if(result.hasErrors())
        {
            model.addAttribute("titulo", "Formulario de producto ********");
            return "productos/form";
        }

        productoDao.Save(producto);

        return "redirect:listar";
    }

    @GetMapping("/editar")
    public String crearEditar(Model model, @RequestParam(name="id", required = true) Long id) {

       
        model.addAttribute("encab", "Editar Productos");
        model.addAttribute("producto",productoDao.search(id));
        model.addAttribute("productoEditar",productoDao.search(id));
        model.addAttribute("titulo2","Producto a editar");
        
    return "productos/actualizar";
    }

    @PostMapping("/editar") 
    public String editar(@Valid Producto productoEditar, BindingResult result, Model model, @RequestParam(name="id", required = true) Long id)
    {

        if(result.hasErrors())
        {
            model.addAttribute("titulo", "Formulario de producto ********");
            return "productos/form";
        }

        productoEditar.setId(id);
        productoDao.update(productoEditar);

        return "redirect:listar";
    }

    @GetMapping("/eliminar")
    public String crearEliminar(Model model, @RequestParam(name="id", required = true) Long id) {

        
        
        model.addAttribute("titulo", "Eliminar Productos");
        model.addAttribute("productoEliminar",productoDao.search(id));
        model.addAttribute("titulo2","Producto a eliminar");
        model.addAttribute("producto", productoDao.search(id));
        
    return "productos/eliminar";
    }

    @PostMapping("/eliminar") 
    public String eliminar(@RequestParam(name="id", required = true) Long id, Model model)
    {
       
        
        productoDao.delete(id);

        return "redirect:listar";
    }

   
    

}
