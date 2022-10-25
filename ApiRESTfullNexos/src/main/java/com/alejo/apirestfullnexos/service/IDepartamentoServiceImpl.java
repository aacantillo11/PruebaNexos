package com.alejo.apirestfullnexos.service;

import com.alejo.apirestfullnexos.domain.Departamento;
import com.alejo.apirestfullnexos.repository.IDepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class IDepartamentoServiceImpl implements ICrudSevice<Departamento>{

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Departamento>> findAll() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        return ResponseEntity.ok(departamentos);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Departamento> findById(Long id) {
        Optional<Departamento> optionalDepartamento = departamentoRepository.findById(id);

        if(optionalDepartamento.isPresent()){
            return ResponseEntity.ok(optionalDepartamento.get());
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @Override
    public ResponseEntity<Departamento> save(Departamento departamento) {
        Departamento saveDepartamento = departamentoRepository.save(departamento);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveDepartamento.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(saveDepartamento);
    }

    @Override
    public ResponseEntity<Departamento> deleteById(Long id) {
        Optional<Departamento> optionalDepartamento = departamentoRepository.findById(id);

        if(optionalDepartamento.isPresent()){
            departamentoRepository.deleteById(optionalDepartamento.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @Override
    public ResponseEntity<Departamento> update(Long id,Departamento departamento) {
        Optional<Departamento> optionalDepartamento = departamentoRepository.findById(id);

        if(optionalDepartamento.isPresent()){
            departamento.setId(optionalDepartamento.get().getId());
            departamentoRepository.save(departamento);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }
}
