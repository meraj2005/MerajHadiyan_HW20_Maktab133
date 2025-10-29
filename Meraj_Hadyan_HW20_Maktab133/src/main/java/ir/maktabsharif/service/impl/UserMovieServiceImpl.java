package ir.maktabsharif.service.impl;

import ir.maktabsharif.model.*;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.service.*;


import java.util.List;
import java.util.Optional;

public class UserMovieServiceImpl extends BaseServiceImpl<Long, UserMovie, UserMovieRepository> implements UserMovieService {

    protected UserMovieRepository userMovieRepository;

    public UserMovieServiceImpl(UserMovieRepository baseRepository) {
        super(baseRepository);
        this.userMovieRepository = baseRepository;
    }

    @Override
    public Optional<UserMovie> findByUserAndMovieId(Long userId, Long movieId) {
        if (userId != null && movieId != null) {
            return userMovieRepository.findByUserAndMovieId(userId, movieId);
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> findMovieByUsernameId(Long userId) {
        return userMovieRepository.findMovieByUsernameId(userId);
    }
}
