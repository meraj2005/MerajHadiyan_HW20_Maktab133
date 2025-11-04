package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.Review;
import ir.maktabsharif.repository.ReviewRepository;
import ir.maktabsharif.util.EntityManagerProvider;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReviewRepositoryImpl extends BaseRepositoryImpl<Long, Review> implements ReviewRepository {
    @Override
    public Class<Review> getEntityClass() {
        return Review.class;
    }

    @Override
    public List<Review> findByMovieId(Long movieId){
        EntityManager em =EntityManagerProvider.getEntityManager();
        return em.createQuery("SELECT r FROM Review r WHERE r.movie.id = :movieId", Review.class)
                .setParameter("movieId", movieId)
                .getResultList();
    }

}
