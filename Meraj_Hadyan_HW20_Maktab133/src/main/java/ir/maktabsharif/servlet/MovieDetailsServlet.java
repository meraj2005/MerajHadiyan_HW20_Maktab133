package ir.maktabsharif.servlet;

import ir.maktabsharif.model.*;
import ir.maktabsharif.repository.impl.*;
import ir.maktabsharif.service.*;
import ir.maktabsharif.service.impl.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

public class MovieDetailsServlet extends HttpServlet {

    private MovieService movieService;
    private ReviewService reviewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        MovieRepositoryImpl movieRepository = new MovieRepositoryImpl();
        ReviewRepositoryImpl reviewRepository = new ReviewRepositoryImpl();

        this.movieService = new MovieServiceImpl(movieRepository);
        this.reviewService = new ReviewServiceImpl(reviewRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long movieId = Long.parseLong(request.getParameter("movieId"));
        Movie movie = movieService.findById(movieId).orElse(null);

        if (movie == null) {
            response.sendRedirect(request.getContextPath() + "/movie-user");
            return;
        }

        List<Review> reviews = reviewService.findByMovieId(movieId);
        double averageRating = reviewService.getAverageRating(movieId);

        request.setAttribute("movie", movie);
        request.setAttribute("reviews", reviews);
        request.setAttribute("averageRating", averageRating);

        request.getRequestDispatcher("movie-details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Long movieId = Long.parseLong(request.getParameter("movieId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        Movie movie = movieService.findById(movieId).orElse(null);
        if (movie == null) {
            response.sendRedirect(request.getContextPath() + "/movie-user");
            return;
        }

        Review review = Review.builder()
                .user(user)
                .movie(movie)
                .rating(rating)
                .comment(comment)
                .build();

        reviewService.save(review);
        response.sendRedirect(request.getContextPath() + "/movie-details?movieId=" + movieId);
    }
}
