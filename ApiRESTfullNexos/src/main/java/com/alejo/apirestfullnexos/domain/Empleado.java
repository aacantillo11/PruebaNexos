package com.alejo.apirestfullnexos.domain;

import com.alejo.apirestfullnexos.auditor.Auditable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "empleados")
public class Empleado extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_documento",nullable = false)
    private String tipoDocumento;

    @Column(name = "numero_documento",nullable = false,unique = true)
    private String numeroDocumento;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String direccion;

    @Column(name = "correo_electronico",unique = true)
    private String correo;

    @Column(length = 10)
    private String telefono;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "departamento_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Departamento departamento;
}
