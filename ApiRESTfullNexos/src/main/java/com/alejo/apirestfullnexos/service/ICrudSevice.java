package com.alejo.apirestfullnexos.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICrudSevice<T> {
    public ResponseEntity<List<T>> findAll();
    public ResponseEntity<T> findById(Long id);
    public ResponseEntity<T> save(T t);
    public ResponseEntity<T> deleteById(Long id);
    public ResponseEntity<T> update(Long id,T t);
}
