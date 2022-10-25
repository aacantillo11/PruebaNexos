package com.alejo.com.webapp.nexos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author KRIPTO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Departamento implements Serializable{
    private Long id;
    private String nombre;
    private String codigo;
    private String fechaHoraCreacion;
    private String fechaHoraModificacion;
    private List<Empleado> empleados = new ArrayList<>();

    public Departamento(Long id, String nombre, String codigo, String fechaHoraCreacion, String fechaHoraModificacion) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.fechaHoraModificacion = fechaHoraModificacion;
        
    }

    public Departamento(Long id) {
        this.id = id;
    }
    
    

    public Departamento(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Departamento(Long id, String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
      
        sb.append("").append(nombre);
     
        return sb.toString();
    }
    
    
    
    
    
    
}
