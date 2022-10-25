package com.alejo.apirestfullnexos.service;

import com.alejo.apirestfullnexos.domain.Departamento;
import com.alejo.apirestfullnexos.domain.Empleado;
import com.alejo.apirestfullnexos.repository.IDepartamentoRepository;
import com.alejo.apirestfullnexos.repository.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class IEmpleadoServiceImpl implements ICrudSevice<Empleado>{

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Empleado>> findAll() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return ResponseEntity.ok(empleados);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Empleado> findById(Long id) {

        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);

        if(optionalEmpleado.isPresent()){
            return ResponseEntity.ok(optionalEmpleado.get());
        }
        return ResponseEntity.unprocessableEntity().build();

    }

    @Override
    @Transactional
    public ResponseEntity<Empleado> save(Empleado empleado) {
        Optional<Departamento> optionalDepartamento = departamentoRepository.findById(empleado.getDepartamento().getId());

        if(optionalDepartamento.isPresent()){
            empleado.setDepartamento(optionalDepartamento.get());
            Empleado saveEmpleado = empleadoRepository.save(empleado);
            URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(saveEmpleado.getId()).toUri();

            return ResponseEntity.created(ubicacion).body(saveEmpleado);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Empleado> deleteById(Long id) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);

        if(optionalEmpleado.isPresent()){
            empleadoRepository.deleteById(optionalEmpleado.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Empleado> update(Long id,Empleado empleado) {
        Optional<Departamento> optionalDepartamento = departamentoRepository.findById(empleado.getDepartamento().getId());
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);

        if (optionalDepartamento.isPresent() && optionalEmpleado.isPresent()){
            empleado.setDepartamento(optionalDepartamento.get());
            empleado.setId(optionalEmpleado.get().getId());
            empleadoRepository.save(empleado);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.unprocessableEntity().build();
    }
}
