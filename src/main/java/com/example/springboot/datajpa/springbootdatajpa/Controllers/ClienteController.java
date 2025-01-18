package com.example.springboot.datajpa.springbootdatajpa.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.IClienteDao;
import com.example.springboot.datajpa.springbootdatajpa.Models.DAO.RolService;
import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Cliente;
import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Rol;
//import com.example.springboot.datajpa.springbootdatajpa.Models.Entity.Rol;
import com.example.springboot.datajpa.springbootdatajpa.enums.RolName;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
//import java.util.List;


@Controller
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;
    
    @Autowired
    private RolService rolService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);


    @GetMapping("/listar")
    public String Listar(Model model) {
        try {
            model.addAttribute("encab", "Clientes");
            model.addAttribute("titulo", "Listado de Clientes");
            List<Cliente> clientes = clienteDao.findAll();
            model.addAttribute("cliente", clientes);
            return "clientes/listar";
        } catch (Exception e) {
            logger.error("Error al listar los clientes", e);
            model.addAttribute("error", "Error al listar los clientes: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/form")
    public String crear(@RequestParam(name = "tab", defaultValue = "cliente") String tab,Model model) {
       
        Cliente cliente = new Cliente();
        cliente.setNombre(cliente.getRolesss());
        model.addAttribute("activeTab", tab);
        model.addAttribute("titulo", "Formulario de Cliente");
        model.addAttribute("cliente", cliente);
       


        return "clientes/form copy";
    }

    //1
    // @PostMapping("/form")
    // public String Guardar(Cliente cliente)
    // {
    //     clienteDao.Save(cliente);

    //     return "redirect:listar";
    // }

    @PostMapping("/form") //para validar se  agrega el valid y el bindingResul, estos siempre deben estar juntos uno tras otro
    public String Guardar(@RequestParam(name = "tab", defaultValue = "cliente") String tab ,@RequestParam(name = "rol", required = true)String rol,@Valid Cliente cliente, BindingResult result, Model model)
    {

         cliente.setRoles(rolService.getByRolNombre(RolName.ROLE_Cliente).get());
        if(!rol.equals("cliente")){

            if(rol.equals("admin")){
                cliente.setRoles(rolService.getByRolNombre(RolName.ROLE_Admin_producto).get());
                cliente.setRoles(rolService.getByRolNombre(RolName.ROLE_Admin_cliente).get());
                cliente.setRoles(rolService.getByRolNombre(RolName.ROLE_ADMIN).get());

            }else{
                if(rol.equals("adminCliente")){
                    cliente.setRoles(rolService.getByRolNombre(RolName.ROLE_Admin_cliente).get());
    
                }else{
                    if(rol.equals("adminProducto")){
                        cliente.setRoles(rolService.getByRolNombre(RolName.ROLE_Admin_producto).get());

        
                    }
                }
            }  
        
        }

        if(result.hasErrors() && !result.hasFieldErrors("roles"))
        {
          
           
            model.addAttribute("titulo", "Formulario de Cliente ********");
            //model.addAttribute("titulo", result.getAllErrors().toString());
            model.addAttribute("activeTab", tab);
            return "clientes/form copy";
        }
        
       


        cliente.setClave(passwordEncoder.encode(cliente.getClave()));

        clienteDao.Save(cliente);

        return "redirect:listar";
    }

    @GetMapping("/eliminar")
    public String crearEliminar(Model model, @RequestParam(name="id", required = true) Long id) {

        
        model.addAttribute("titulo", "Eliminar clientes");
        model.addAttribute("clienteEliminar",clienteDao.search(id));
        model.addAttribute("titulo2", "Cliente a eliminar");

        model.addAttribute("cliente", clienteDao.search(id));
        
        
    return "clientes/eliminar";
    }

    @PostMapping("/eliminar") 
    public String eliminar(Model model, @RequestParam(name="id", required = true) Long id)
    {

        
        clienteDao.delete(id);

        return "redirect:listar";
    }



    @GetMapping("/editar")
    public String crearEditar(Model model, @RequestParam(name = "rol", defaultValue = "ROLE_Cliente") String rol, @RequestParam(name="id", required = true) Long id) {

        Cliente cliente= clienteDao.search(id);

        
        model.addAttribute("titulo", "Editar clientes");
        model.addAttribute("cliente", cliente);
        model.addAttribute("activeTab", rol);
        model.addAttribute("titulo2","Cliente a editar");
        
        
    return "clientes/actualizar";
    }

    @PostMapping("/editar") 
    public String editar(@Valid Cliente cliente, BindingResult result, Model model,@RequestParam(name = "rol", defaultValue = "ROLE_Cliente") String rol , @RequestParam(name="id", required = true) Long id )
    {
        Set<Rol> roles= new HashSet<>();
       
        Rol rol2= rolService.getByRolNombre(RolName.ROLE_Cliente).get();
        cliente.setId(id);
        roles.add(rol2);
        if(result.hasErrors() && !result.hasFieldErrors("roles"))
        {
          
           
            model.addAttribute("titulo", "Formulario de Cliente ********");
            //model.addAttribute("titulo", result.getAllErrors().toString());
            model.addAttribute("activeTab", rol);
            return "clientes/actualizar";
        }
        cliente.setRoles(roles);
        if(!rol.equals("ROLE_Cliente")){

            if(rol.equals("ROLE_ADMIN")){
                rol2= rolService.getByRolNombre(RolName.ROLE_Admin_producto).get();
                roles.add(rol2);
                rol2= rolService.getByRolNombre(RolName.ROLE_Admin_cliente).get();
                roles.add(rol2);
                rol2= rolService.getByRolNombre(RolName.ROLE_ADMIN).get();
                roles.add(rol2);
            }else{
                if(rol.equals("ROLE_Admin_cliente")){
                    cliente.setRoles(rolService.getByRolNombre(RolName.ROLE_Admin_cliente).get());
    
                }else{
                    if(rol.equals("ROLE_Admin_producto")){
                        cliente.setRoles(rolService.getByRolNombre(RolName.ROLE_Admin_producto).get());

        
                    }
                }
            }  
        
        }

        cliente.setClave(passwordEncoder.encode(cliente.getClave()));

        clienteDao.update(cliente);

        return "redirect:listar";
    }

    @GetMapping("/home")
    public String home(Model model) {

        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {

        
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "login";
    }

    @GetMapping("/singup")
    public String singup(Model model) {

        Cliente cliente=new Cliente();
        model.addAttribute("cliente", cliente);
        return "singup";
    }

    @PostMapping("/formm") //para validar se  agrega el valid y el bindingResul, estos siempre deben estar juntos uno tras otro
    public String registrar(@Valid Cliente cliente, BindingResult result, Model model)
    {

        Rol rol=rolService.getByRolNombre(RolName.ROLE_Cliente).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        cliente.setRoles(roles);
        cliente.setStatus(false);
        if(result.hasErrors() && !result.hasFieldErrors("roles") && !result.hasFieldErrors("status"))
        {
          
           
            model.addAttribute("cliente",cliente);
            return "singup";
        }
        
       


        cliente.setClave(passwordEncoder.encode(cliente.getClave()));

        clienteDao.Save(cliente);

        model.addAttribute("registroOK", "Cuenta Creada " + cliente.getNombre());
        return "login";
    }

    @GetMapping("/forbidden")
    public String forbidden(Model model) {

        

        return "forbidden";
    }
   

}


