package com.alejo.apirestfullnexos.controller;

import com.alejo.apirestfullnexos.domain.Departamento;
import com.alejo.apirestfullnexos.service.ICrudSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/apinexos/departamentos")
public class DepartamentoController {

    @Autowired
    private ICrudSevice<Departamento> departamentoSevice;

    @GetMapping
    public ResponseEntity<List<Departamento>> getAll() {
        ResponseEntity<List<Departamento>> response = departamentoSevice.findAll();
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getById(@PathVariable("id") Long id) {
        ResponseEntity<Departamento> response = departamentoSevice.findById(id);
        return response;
    }

    @PostMapping
    public ResponseEntity<Departamento> create(@Valid @RequestBody Departamento departamento) {
        ResponseEntity<Departamento> response = departamentoSevice.save(departamento);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Departamento> deleteById(@PathVariable("id") Long id) {
        ResponseEntity<Departamento> response = departamentoSevice.deleteById(id);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> update(@PathVariable("id") Long id, @Valid @RequestBody Departamento departamento) {
        ResponseEntity<Departamento> response = departamentoSevice.update(id,departamento);
        return response;
    }

}
