/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alejo.com.webapp.nexos.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author KRIPTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado implements Serializable{
    
    private Long id;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String ciudad;
    private String direccion;
    private String correo;
    private String telefono;
    private String fechaHoraCreacion;
    private String fechaHoraModificacion;
    private Departamento departamento;
    
    private String nombreDepartamento;

    public Empleado(Long id, String tipoDocumento, String numeroDocumento, String nombres, String apellidos, String ciudad, String direccion, String correo, String telefono, String fechaHoraCreacion, String fechaNoraModificacion) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.fechaHoraModificacion = fechaNoraModificacion;
    }

    public Empleado(String tipoDocumento, String numeroDocumento, String nombres, String apellidos, String ciudad, String direccion, String correo, String telefono, String nombreDepartamento) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.nombreDepartamento = nombreDepartamento;
    }

    public Empleado(Long id, String tipoDocumento, String numeroDocumento, String nombres, String apellidos, String ciudad, String direccion, String correo, String telefono, String nombreDepartamento) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.nombreDepartamento = nombreDepartamento;
    }
    
    
    
}
