package com.alejo.apirestfullnexos.domain;

import com.alejo.apirestfullnexos.auditor.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departamentos")
public class Departamento extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(unique = true,nullable = false)
    private String codigo;

    @Getter
    @Setter
    @Column(unique = true,nullable = false)
    private String nombre;

    @Getter
    @OneToMany(mappedBy = "departamento",cascade = CascadeType.ALL)
    private List<Empleado> empleados = new ArrayList<>();

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
        for(Empleado empleado:empleados){
            empleado.setDepartamento(this);
        }
    }
}
