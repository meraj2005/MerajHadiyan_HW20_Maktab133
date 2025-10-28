package ir.maktabsharif.repository;

import ir.maktabsharif.model.*;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<ID, T extends BaseModel<ID>>{
    T save(T type);

    T update(T type);

    boolean deleteById (ID id);

    Optional<T> findById (ID id);

    List<T> findAll();

    Class<T> getEntityClass();
}
