package ir.maktabsharif.service;

import ir.maktabsharif.model.Review;

import java.util.List;

public interface ReviewService extends BaseService<Long,Review> {
    List<Review> findByMovieId(Long movieId);
    double getAverageRating(Long movieId);
}
