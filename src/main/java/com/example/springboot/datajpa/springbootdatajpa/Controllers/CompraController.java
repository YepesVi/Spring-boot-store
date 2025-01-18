package com.example.springboot.datajpa.springbootdatajpa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.ClienteDaoImp;
import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.CompraDaoImp;
import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.DetalleDaoImp;
import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.ProductoDaoImp;
import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Compra;
import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Detalle;
import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Producto;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;






@Controller
public class CompraController {

    @Autowired
    CompraDaoImp compraDao;
    @Autowired
    DetalleDaoImp detalleDao;
    @Autowired
    ClienteDaoImp clienteDao;
    @Autowired
    ProductoDaoImp productoDao;

   
    
    @GetMapping("/compras")
    public String crearCompra(@RequestParam (name="cliente_id",required = true) Long id, Model model) {
        model.addAttribute("encab", "Compras");
        model.addAttribute("cliente",clienteDao.search(id));
        model.addAttribute("titulo", "Compra");
        model.addAttribute("titulo2", "Comprador");
        Compra compra =new Compra();
        compra.setCliente(clienteDao.search(id));        
        compraDao.Save(compra);
        model.addAttribute("compra", compra);
        return "compras/compras";
    
    }

    @PostMapping("/compras")
    public String Cancelar(@RequestParam (name="compra_id",required = true) Long compraId) {
        Compra compra = new Compra();
        compra.setId(compraId);
        compraDao.delete(compra);
        return "redirect:listar";
    }
    

    @GetMapping("/compra")
    public String crearCompra(@RequestParam (name="cliente_id",required = true) Long id, @RequestParam(name="compra_id",required = true) Long idc, Model model) {
        model.addAttribute("cliente",clienteDao.search(id));
        model.addAttribute("titulo", "Compra");
        model.addAttribute("titulo2", "Comprador");
        Compra compra = compraDao.search(idc);
        compra.setCliente(clienteDao.search(id));  
        model.addAttribute("cliente", compra.getCliente());    
        model.addAttribute("compra", compra);
        model.addAttribute("producto", productoDao.findAll());
        return "compras/cproductos";
    
    }
    @GetMapping("/agregar")
    public String crearadd(@RequestParam (name="cliente_id",required = true) Long id, @RequestParam(name="compra_id",required = true) Long idc,@RequestParam(name="id",required = true) Long idp, Model model) {
        model.addAttribute("producto", productoDao.search(idp));
        Compra compra = new Compra();
        compra= compraDao.search(idc);
        model.addAttribute("compra", compra);
        model.addAttribute("cliente", clienteDao.search(id));
        Detalle detalle= new Detalle();
        detalle.setCompra(compra);
        detalle.setProducto(productoDao.search(idp));
        detalle.setValor(0);
        detalle.setDescuento(0);
        model.addAttribute("detalle", detalle);



        return "compras/cproducto";
    
    }

    
    @PostMapping("/agreg")  
    public String guardar(@RequestParam (name="cliente_id",required = true) Long id, @RequestParam(name="compra_id",required = true) Long idc,@RequestParam(name="id",required = true) Long idp, Model model, @Valid Detalle detalle, BindingResult result) {
        if( detalle.getCantidad()<=productoDao.search(idp).getStock() && detalle.getCantidad()>0)
        {
        
        detalle.setValor((float)productoDao.search(idp).getValorUnidad()*detalle.getCantidad());
        detalle.setDescuento((float)0);
        if(detalle.getCantidad()>=10)
        {
            detalle.setDescuento((float) 0.1);
            detalle.setValor((float)detalle.getValor()-detalle.getValor()*detalle.getDescuento());
        }
        productoDao.subStock(idp, detalle.getCantidad());
        detalle.setId(null);
        detalle.setCompra(compraDao.search(idc));
        detalle.setProducto(productoDao.search(idp));
        detalleDao.Save(detalle);
        Compra compra=compraDao.search(idc);
        compra.setValor(detalleDao.valorDes(compraDao.search(idc)));
        compra.setSubTotal(detalleDao.valorTot(compraDao.search(idc)));
        compra.setDescuento(detalleDao.DescTot(compra));
        compraDao.update(compra);
        model.addAttribute("cliente", clienteDao.search(id));
        model.addAttribute("compra", compraDao.search(idc));
        model.addAttribute("detalle", detalleDao.find(compraDao.search(idc)));
        model.addAttribute("titulo", "Compra");
        model.addAttribute("titulo2", "Comprador");
        return "compras/compras";
        }
        
        model.addAttribute("cliente", clienteDao.search(id));
        model.addAttribute("compra", compraDao.search(idc));
        model.addAttribute("detalle", detalleDao.find(compraDao.search(idc)));
        model.addAttribute("titulo", "Compra");
        model.addAttribute("titulo2", "Comprador");
        model.addAttribute("titulo3", "No se pudo agregar el producto, debido a que supera el stock disponible");
        if(detalle.getCantidad()<1);
        {
            model.addAttribute("titulo3", "No se pudo agregar el producto, supera el stock disponible");
        }
        return "compras/compras";
    
    }

    @GetMapping("/quitar")
    public String quitarProducto(@RequestParam (name="cliente_id",required = true) Long id,@RequestParam(name="compra_id",required = true) Long idc, @RequestParam (name = "detalle_id") Long idd, Model model) {
        Detalle detalle= new Detalle();
        detalle=detalleDao.search(idd);
        Producto producto= new Producto();
        producto= productoDao.search(detalle.getProducto().getId());
        producto.setStock(producto.getStock()+detalle.getCantidad());
        productoDao.update(producto);
        detalleDao.delete(detalle);
        Compra compra=compraDao.search(idc);
        compra.setValor(detalleDao.valorDes(compraDao.search(idc)));
        compra.setSubTotal(detalleDao.valorTot(compraDao.search(idc)));
        compra.setDescuento(detalleDao.DescTot(compra));
        compraDao.update(compra);
        model.addAttribute("cliente", clienteDao.search(id));
        model.addAttribute("compra", compraDao.search(idc));
        model.addAttribute("detalle", detalleDao.find(compraDao.search(idc)));
        model.addAttribute("titulo", "Compra");
        model.addAttribute("titulo2", "Comprador");
        return "compras/compras";
    }

    @GetMapping("/facturas")
    public String verFacturas( Model model) {
        model.addAttribute("encab", "Facturas");
        model.addAttribute("titulo", "Facturas");
        model.addAttribute("compra", compraDao.findAll());
       
        return "compras/facturas";
    }
    
    

    @GetMapping("/detalle")
    public String verDetalles(@RequestParam(name="compra_id", required = true)Long idc, Model model) {
        model.addAttribute("titulo", "Detalle-factura");
        model.addAttribute("titulo2", "Encabezado");
        model.addAttribute("titulo3", "Detalle");
        Compra compra = compraDao.search(idc);
        model.addAttribute("compra", compra);
        model.addAttribute("detalle", detalleDao.find(compra));
        
        return "compras/detalle";
    }
    
    
    
}
