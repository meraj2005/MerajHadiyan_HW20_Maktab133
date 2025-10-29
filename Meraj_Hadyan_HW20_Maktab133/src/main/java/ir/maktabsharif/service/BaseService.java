package ir.maktabsharif.service;

import ir.maktabsharif.model.BaseModel;

import java.util.List;
import java.util.Optional;

public interface BaseService <ID, T extends BaseModel<ID>> {
    T save(T type);

    T update (T type);

    boolean deleteById (ID id);

    Optional<T> findById (ID id);

    List<T> findAll();
}
