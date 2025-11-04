package ir.maktabsharif.servlet;

import ir.maktabsharif.model.*;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.repository.impl.*;
import ir.maktabsharif.service.*;
import ir.maktabsharif.service.impl.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


public class AllMovieUserServlet extends HttpServlet {
    private MovieService movieService;
    private UserMovieService userMovieService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        MovieRepository movieRepository = new MovieRepositoryImpl();
        UserMovieRepository userMovieRepository = new UserMovieRepositoryImpl();
        this.movieService = new MovieServiceImpl(movieRepository);
        this.userMovieService = new UserMovieServiceImpl(userMovieRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");


        List<Movie> movies = movieService.findAll();
        List<Movie> watchlistMovie = userMovieService.findMovieByUsernameId(user.getId());
        //Genre
        String category = request.getParameter("category");

        if(category != null && !category.isEmpty()){
            movies=movieService.filterGenreMovie(category);
        }
        request.setAttribute("movies", movies);
        request.setAttribute("selectedCategory", category);


        request.setAttribute("user",user);

        request.setAttribute("watchlist",watchlistMovie);

        request.getRequestDispatcher("movie-user.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        String movieIdStr = request.getParameter("movieId");

        if (movieIdStr == null || movieIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/movie-user");
            return;
        }

        Long movieId = Long.parseLong(movieIdStr);
        Long userId = user.getId();


        Movie movie = movieService.findById(movieId).orElse(null);
        if (movie == null) {
            response.sendRedirect(request.getContextPath() + "/movie-user");
            return;
        }

        UserMovie userMovie1 = userMovieService.findByUserAndMovieId(userId, movieId).orElse(null);

        if (userMovie1 != null) {
            response.sendRedirect(request.getContextPath() + "/movie-user");
            return;
        }

        userMovieService.save(UserMovie.builder().user(user).movie(movie).build());
        session.setAttribute("user", user);


        response.sendRedirect(request.getContextPath() + "/movie-user");

    }

}