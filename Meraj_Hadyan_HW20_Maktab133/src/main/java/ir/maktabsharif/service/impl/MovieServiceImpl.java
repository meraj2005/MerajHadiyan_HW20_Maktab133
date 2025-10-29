package ir.maktabsharif.service.impl;

import ir.maktabsharif.model.*;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.service.*;

import java.util.Optional;

public class MovieServiceImpl extends BaseServiceImpl<Long, Movie, MovieRepository> implements MovieService {
    public MovieServiceImpl(MovieRepository baseRepository) {
        super(baseRepository);
    }
}
