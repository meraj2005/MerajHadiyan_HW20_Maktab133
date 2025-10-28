package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.*;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.util.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserMovieRepositoryImpl extends BaseRepositoryImpl<Long, UserMovie> implements UserMovieRepository {
    @Override
    public Class<UserMovie> getEntityClass() {
        return UserMovie.class;
    }


    @Override
    public Optional<UserMovie> findByUserAndMovieId(Long userId, Long movieId) {
        return EntityManagerProvider
                        .getEntityManager()
                        .createQuery("select u from UserMovie u where u.user.id = :userId and u.movie.id = :movieId", UserMovie.class)
                        .setParameter("userId", userId)
                        .setParameter("movieId", movieId)
                        .getResultStream()
                        .findFirst();
    }

    @Override
    public List<Movie> findMovieByUsernameId(Long userId) {
        return EntityManagerProvider
                .getEntityManager()
                .createQuery("select um.movie from UserMovie um where um.user.id = :userId", Movie.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
