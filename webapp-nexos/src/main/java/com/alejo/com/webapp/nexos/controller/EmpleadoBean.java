package com.alejo.com.webapp.nexos.controller;

import com.alejo.com.webapp.nexos.domain.Departamento;
import com.alejo.com.webapp.nexos.domain.Empleado;
import com.alejo.com.webapp.nexos.service.ICrudService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.primefaces.PrimeFaces;

/**
 *
 * @author KRIPTO
 */
@Named
@RequestScoped
public class EmpleadoBean implements Serializable{
    
    private int codeHttp = 0; 
    private static Long  id;
    
    private Empleado empleado = new Empleado();
    
    private List<Empleado> empleados;
   
    private static  List<String> listaDepartamento = new ArrayList<>();
    private static Map<String,Long> posicionDepartamento = new HashMap<>();
    
    private static Empleado selectedEmpleado;

    private List<Departamento> departamentos;
     
    
    
    @Inject
    private ICrudService<Departamento> serviceDepartamento;
    
     @Inject
    private ICrudService<Empleado> service;
     
     
    @PostConstruct
    public void init() {
        this.departamentos = this.serviceDepartamento.findAll();
        this.empleados = this.service.findAll();
       posicionDepartamento = new HashMap<>();
        List<Departamento> departamentosList = serviceDepartamento.findAll();
        departamentosList.forEach(v -> { 
            listaDepartamento.add(v.getNombre());
            posicionDepartamento.put(v.getNombre(),v.getId());
        });
        
    }
     

   /* public EmpleadoBean() {
        listaDepartamento = new ArrayList<>();
        posicionDepartamento = new HashMap<>();
        List<Departamento> departamentosList = serviceDepartamento.findAll();
        departamentosList.forEach(v -> { 
            listaDepartamento.add(v.getNombre());
            posicionDepartamento.put(v.getNombre(),v.getId());
        });
    }*/

    
    public void openNew() {

        selectedEmpleado = new Empleado();
    }
    
    public void addEmpleado() {
              
        Long idDepartamento=0L;
        
        if (selectedEmpleado.getId() == null) {
           String  nombres = this.empleado.getNombres();
            String apellidos = this.empleado.getApellidos();
            String tipoDoc = this.empleado.getTipoDocumento();
            String numeroDoc = this.empleado.getNumeroDocumento();
            String ciudad = this.empleado.getCiudad();
            String direccion = this.empleado.getDireccion();
            String correo = this.empleado.getCorreo();
            String telefono = this.empleado.getTelefono();
            String nombreDepartamnto = this.empleado.getNombreDepartamento();
            idDepartamento = posicionDepartamento.get(nombreDepartamnto);
            Empleado newEmpleado = new Empleado(tipoDoc,numeroDoc,nombres,apellidos,ciudad,direccion,correo,telefono,nombreDepartamnto);
            newEmpleado.setDepartamento(new Departamento(idDepartamento));
            codeHttp = service.save(newEmpleado);
            if (codeHttp == 201) {
                showMessage(FacesMessage.SEVERITY_INFO, "Guarda", "Registro exitso");
            } else {
                showMessage(FacesMessage.SEVERITY_ERROR, "Guarda", "Registro no exitoso");
            }

        } else {
            Optional<Empleado> optionalEmpleado = service.findById(EmpleadoBean.id);
            if (optionalEmpleado.isPresent()) {
                
                Empleado updateEmpleado = new Empleado(EmpleadoBean.id, this.empleado.getTipoDocumento(), 
                        this.empleado.getNumeroDocumento(),this.empleado.getNombres(),this.empleado.getApellidos(),
                this.empleado.getCiudad(),this.empleado.getDireccion(),this.empleado.getCorreo(),this.empleado.getTelefono(),
                this.empleado.getNombreDepartamento());
                codeHttp = service.update(updateEmpleado);

                if (codeHttp == 204) {
                    showMessage(FacesMessage.SEVERITY_INFO, "Actualiza", "Actualización exitsa");
                } else {
                    showMessage(FacesMessage.SEVERITY_ERROR, "Actualiza", "Actualización no exitosa");
                }
            } else {
                showMessage(FacesMessage.SEVERITY_ERROR, "Actualiza", "Actualización no exitosa");
            }
        }

        selectedEmpleado = new Empleado();
        this.empleados = this.service.findAll();
    }
    
    
    public void deleteEmpleado(Empleado empleado) {
       
        codeHttp = service.delete(empleado.getId());
        if (codeHttp == 204) {
            showMessage(FacesMessage.SEVERITY_WARN, "Elimina", "Elimina exitosamente");
        } else {
            showMessage(FacesMessage.SEVERITY_ERROR, "Elimina", "Elimina no exitoso");
        }
        this.empleados = this.service.findAll();
    }
    
    public void updateEmpleado(Empleado empleado) {
        
        EmpleadoBean.id = empleado.getId();
        selectedEmpleado = new Empleado(100L, "A", "A","A","A","A","A","A","A","A","A");
        PrimeFaces.current().executeScript("PF('manageEmployeeDialog').show()");
    }
    
    public void showMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        EmpleadoBean.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public static List<String> getListaDepartamento() {
        return listaDepartamento;
    }

    public static void setListaDepartamento(List<String> listaDepartamento) {
        EmpleadoBean.listaDepartamento = listaDepartamento;
    }

    public static Map<String, Long> getPosicionDepartamento() {
        return posicionDepartamento;
    }

    public static void setPosicionDepartamento(Map<String, Long> posicionDepartamento) {
        EmpleadoBean.posicionDepartamento = posicionDepartamento;
    }

    public static Empleado getSelectedEmpleado() {
        return selectedEmpleado;
    }

    public static void setSelectedEmpleado(Empleado selectedEmpleado) {
        EmpleadoBean.selectedEmpleado = selectedEmpleado;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
     
     
    

    
    
}
