package com.alejo.com.webapp.nexos.service;

import jakarta.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author KRIPTO
 */
@Local
public interface ICrudService<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    int save(T t);
    int update(T t);
    int delete(Long id);
}
