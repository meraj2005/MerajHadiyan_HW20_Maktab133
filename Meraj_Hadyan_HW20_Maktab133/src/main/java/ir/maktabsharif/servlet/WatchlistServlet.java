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
import java.util.stream.Collectors;

public class WatchlistServlet extends HttpServlet {
    private UserMovieService userMovieService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        UserMovieRepository userMovieRepository = new UserMovieRepositoryImpl();
        this.userMovieService = new UserMovieServiceImpl(userMovieRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        List<Movie> watchlistMovie = userMovieService.findMovieByUsernameId(user.getId());
        //Genre
        String category = request.getParameter("category");

        if(category != null && !category.isEmpty()){
            watchlistMovie=watchlistMovie.stream()
                    .filter(m -> m.getGenre() != null && m.getGenre().equalsIgnoreCase(category))
                    .collect(Collectors.toList());;
        }
        request.setAttribute("selectedCategory", category);
        request.setAttribute("user",user);
        request.setAttribute("watchlist",watchlistMovie);
        request.getRequestDispatcher("watchlist.jsp").forward(request,response);

    }
}