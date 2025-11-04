package ir.maktabsharif.repository;

import ir.maktabsharif.model.Review;

import java.util.List;

public interface ReviewRepository extends BaseRepository<Long, Review>{
    public List<Review> findByMovieId(Long movieId);
}
