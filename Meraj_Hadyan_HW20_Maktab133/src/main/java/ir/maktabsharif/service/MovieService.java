package ir.maktabsharif.service;

import ir.maktabsharif.model.*;

import java.util.List;


public interface MovieService extends BaseService<Long, Movie>{
    //Genre
    public List<Movie> filterGenreMovie(String category);
}
