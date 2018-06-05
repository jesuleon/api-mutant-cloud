package com.mercadolibre.xmen.domain.repository;

/**
 * Created by jesus.leon on 29/05/18.
 */
public interface ObjectifyRepository<T> {
    T save(T t);
}
