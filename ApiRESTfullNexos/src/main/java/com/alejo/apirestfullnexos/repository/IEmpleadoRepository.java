package com.alejo.apirestfullnexos.repository;

import com.alejo.apirestfullnexos.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado,Long> {
}
