package ir.maktabsharif.service.impl;

import ir.maktabsharif.model.Review;
import ir.maktabsharif.repository.ReviewRepository;
import ir.maktabsharif.repository.impl.ReviewRepositoryImpl;
import ir.maktabsharif.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl extends BaseServiceImpl<Long, Review, ReviewRepository> implements ReviewService {
    public ReviewServiceImpl(ReviewRepository reviewRepository){
        super(reviewRepository);
    }

    @Override
    public List<Review> findByMovieId(Long movieId) {
        ReviewRepository reviewRepository=new ReviewRepositoryImpl();
        return reviewRepository.findByMovieId(movieId);
    }

    @Override
    public double getAverageRating(Long movieId) {
        ReviewRepository reviewRepository=new ReviewRepositoryImpl();
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        if (reviews.isEmpty()) return 0.0;
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    }
}
