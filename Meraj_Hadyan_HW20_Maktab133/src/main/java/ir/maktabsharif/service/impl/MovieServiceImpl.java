package ir.maktabsharif.service.impl;

import ir.maktabsharif.model.*;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.repository.impl.MovieRepositoryImpl;
import ir.maktabsharif.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class MovieServiceImpl extends BaseServiceImpl<Long, Movie, MovieRepository> implements MovieService {
    public MovieServiceImpl(MovieRepository baseRepository) {
        super(baseRepository);
    }
    //Genre
    @Override
    public List<Movie> filterGenreMovie(String category){
        MovieRepositoryImpl movieRepository=new MovieRepositoryImpl();
        MovieServiceImpl movieService = new MovieServiceImpl(movieRepository);
        List<Movie> movies = movieService.findAll();
        movies = movies.stream()
                .filter(m -> m.getGenre() != null && m.getGenre().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        return movies;
    }



}
