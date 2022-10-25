package com.alejo.apirestfullnexos.repository;

import com.alejo.apirestfullnexos.domain.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentoRepository extends JpaRepository<Departamento,Long> {
}
