package com.alejo.apirestfullnexos.controller;

import com.alejo.apirestfullnexos.domain.Departamento;
import com.alejo.apirestfullnexos.domain.Empleado;
import com.alejo.apirestfullnexos.service.ICrudSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/apinexos/empleados")
public class EmpleadoController {
    @Autowired
    private ICrudSevice<Departamento> departamentoSevice;

    @Autowired
    private ICrudSevice<Empleado> empleadoSevice;

    @GetMapping
    public ResponseEntity<List<Empleado>> getAll() {
        ResponseEntity<List<Empleado>> response = empleadoSevice.findAll();
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getById(@PathVariable("id") Long id) {
        ResponseEntity<Empleado> response = empleadoSevice.findById(id);
        return response;
    }

    @PostMapping
    public ResponseEntity<Empleado> create(@Valid @RequestBody Empleado empleado) {
        ResponseEntity<Empleado> response = empleadoSevice.save(empleado);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> deleteById(@PathVariable("id") Long id) {
        ResponseEntity<Empleado> response = empleadoSevice.deleteById(id);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable("id") Long id, @Valid @RequestBody Empleado empleado) {
        ResponseEntity<Empleado> response = empleadoSevice.update(id,empleado);
        return response;
    }

}
