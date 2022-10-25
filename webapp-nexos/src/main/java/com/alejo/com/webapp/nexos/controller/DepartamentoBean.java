package com.alejo.com.webapp.nexos.controller;

import com.alejo.com.webapp.nexos.domain.Departamento;
import com.alejo.com.webapp.nexos.service.ICrudService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.primefaces.PrimeFaces;

/**
 *
 * @author KRIPTO
 */
@Named
@RequestScoped
public class DepartamentoBean implements Serializable {

    private int codeHttp = 0;

    private Departamento departamento = new Departamento();

    private static Long  id;

    private static Departamento selectedDepartamento;

    private List<Departamento> departamentos;

    @Inject
    private ICrudService<Departamento> service;
    
   

    @PostConstruct
    public void init() {
        this.departamentos = this.service.findAll();
        
    }

      

    public void openNew() {

        selectedDepartamento = new Departamento();
    }

    public void addDepartamento() {
        String nombre = "";
        String codigo = "";
        if (selectedDepartamento.getId() == null) {
            nombre = this.departamento.getNombre();
            codigo = this.departamento.getCodigo();
            Departamento newDepartamento = new Departamento(nombre, codigo);
            codeHttp = service.save(newDepartamento);
            if (codeHttp == 201) {
                showMessage(FacesMessage.SEVERITY_INFO, "Guarda", "Registro exitso");
            } else {
                showMessage(FacesMessage.SEVERITY_ERROR, "Guarda", "Registro no exitoso");
            }

        } else {
            Optional<Departamento> optionalDepartamento = service.findById(DepartamentoBean.id);
            if (optionalDepartamento.isPresent()) {
                Departamento updateDepartamento = new Departamento(DepartamentoBean.id, this.departamento.getNombre(), this.departamento.getCodigo());
                codeHttp = service.update(updateDepartamento);

                if (codeHttp == 204) {
                    showMessage(FacesMessage.SEVERITY_INFO, "Actualiza", "Actualización exitsa");
                } else {
                    showMessage(FacesMessage.SEVERITY_ERROR, "Actualiza", "Actualización no exitosa");
                }
            } else {
                showMessage(FacesMessage.SEVERITY_ERROR, "Actualiza", "Actualización no exitosa");
            }
        }

        selectedDepartamento = new Departamento();
        this.departamentos = this.service.findAll();
    }

    public void deleteDepartamento(Departamento departamento) {
       
        codeHttp = service.delete(departamento.getId());
        if (codeHttp == 204) {
            showMessage(FacesMessage.SEVERITY_WARN, "Elimina", "Elimina exitosamente");
        } else {
            showMessage(FacesMessage.SEVERITY_ERROR, "Elimina", "Elimina no exitoso");
        }
        this.departamentos = this.service.findAll();
    }

    public void updateDepartamento(Departamento departamento) {
        
        DepartamentoBean.id = departamento.getId();
        selectedDepartamento = new Departamento(100L, "A", "A");
        PrimeFaces.current().executeScript("PF('manageDepartmentDialog').show()");
    }

    public void showMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public static Departamento getSelectedDepartamento() {
        return selectedDepartamento;
    }

    public static void setSelectedDepartamento(Departamento selectedDepartamento) {
        DepartamentoBean.selectedDepartamento = selectedDepartamento;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        DepartamentoBean.id = id;
    }

  
    

}
