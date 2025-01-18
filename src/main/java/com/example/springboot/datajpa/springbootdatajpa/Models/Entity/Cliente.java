package com.example.springboot.datajpa.springbootdatajpa.Models.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.springboot.datajpa.springbootdatajpa.enums.RolName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
//import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
// @Table(name="clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty //que es requerido u obligatorio
    private String nombre;

    @NotEmpty
    @Size(min=4, max=15)
    private String apellido;

    @NotEmpty
    @Email
    @Column(unique = true, name = "email")
    private String email;

    @NotEmpty
    private String clave;

    @NotNull
    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cliente_rol", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
    

    public Set<Rol> getRoles() {
        return roles;
    }

    public String getRoless() {
        
        StringBuilder nombres = new StringBuilder();
    for (Rol rol : roles) {
        nombres.append(rol.getRolName());
        nombres.append(", "); // Añade una coma y un espacio para separar los nombres
    }
    if (nombres.length() > 0) {
        nombres.setLength(nombres.length() - 2); // Elimina la última coma y espacio
    }
    return nombres.toString();
        
    }

    public String getRolesss() {
        return this.getHighestPriorityRole(this.roles);
    }

    private String getHighestPriorityRole(Set<Rol> roles) {
        // Definir un mapa que asocia cada rol con una prioridad numérica
        Map<String, Integer> rolePriority = new HashMap<>();
        rolePriority.put("ROLE_Cliente", 1);
        rolePriority.put("ROLE_Admin_producto", 2);
        rolePriority.put("ROLE_Admin_cliente", 3);
        rolePriority.put("ROLE_ADMIN", 4);

        // Inicializar variables para rastrear el rol de mayor prioridad
        String highestPriorityRole = null;
        int highestPriority = -1;

        // Iterar sobre el conjunto de roles
        for (Rol role : roles) {
            // Si el rol actual tiene una prioridad más alta, actualizar las variables
            if (rolePriority.containsKey(role.getRolName().name()) && rolePriority.get(role.getRolName().name()) > highestPriority) {
                highestPriority = rolePriority.get(role.getRolName().name());
                highestPriorityRole = role.getRolName().name();
            }
        }

        // Devolver el rol de mayor importancia
        return highestPriorityRole;
    }

    
    public void setRoles(Rol roles) {
        this.roles.add(roles);
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }



    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }


    

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    //@NotNull //valida que la fecha no sea nula
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date CreateAt;

    @PrePersist
    public void perPersist(){

        CreateAt= new Date();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;


    public Cliente() {
        Rol rol = new Rol(RolName.ROLE_Cliente);
        this.roles.add(rol);
        this.status = false;
    }

    

}
