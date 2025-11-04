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

public class AllMovieAdminServlet extends HttpServlet {
    private MovieService movieService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        MovieRepository movieRepository = new MovieRepositoryImpl();
        this.movieService = new MovieServiceImpl(movieRepository);

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        List<Movie> movies = movieService.findAll();
        //Genre
        String category = request.getParameter("category");

        if(category != null && !category.isEmpty()){
            movies=movieService.filterGenreMovie(category);
        }
        request.setAttribute("movies", movies);
        request.setAttribute("selectedCategory", category);



        request.setAttribute("user",user);
        request.setAttribute("movies",movies);
        request.getRequestDispatcher("movie-admin.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String movieIdStr = request.getParameter("movieId");
        Long movieId = Long.parseLong(movieIdStr);

        if ("delete".equals(action) && !movieIdStr.isEmpty()) {
            movieService.deleteById(movieId);
            response.sendRedirect(request.getContextPath() + "/admin");
        } else {
            response.sendRedirect(request.getContextPath() + "/movie-admin");
        }
    }
}