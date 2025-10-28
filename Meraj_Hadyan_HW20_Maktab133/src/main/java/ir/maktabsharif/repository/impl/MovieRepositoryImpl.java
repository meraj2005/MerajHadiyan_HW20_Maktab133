package ir.maktabsharif.repository.impl;

import jakarta.persistence.EntityManager;
import ir.maktabsharif.model.*;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.util.*;

import java.util.Optional;

public class MovieRepositoryImpl extends BaseRepositoryImpl<Long, Movie> implements MovieRepository {
    @Override
    public Class<Movie> getEntityClass() {
        return Movie.class;
    }
}
