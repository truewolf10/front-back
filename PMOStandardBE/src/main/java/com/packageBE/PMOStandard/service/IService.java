package com.packageBE.PMOStandard.service;

public interface IService<T,K>{
    void save(T entity);
    void remove(K key);
    Iterable<T> getAll();
    T getById(K key);
    int count();
}
