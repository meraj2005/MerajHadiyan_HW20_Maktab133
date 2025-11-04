<%@ page import="ir.maktabsharif.model.User" %>
<%@ page import="ir.maktabsharif.model.Movie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 10/26/2025
  Time: 8:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Movie Library</title>
    <link rel="stylesheet" href="css2/movie-user.css">
</head>
<body>

<form action="<%=request.getContextPath() + "/movie-user"%>" method="get" class="filter-form">
    <label for="category">Filter by Category:</label>
    <select name="category" id="category" onchange="this.form.submit()">
        <option value="">All</option>
        <option value="Action" <%= "Action".equals(request.getAttribute("selectedCategory")) ? "selected" : "" %>>Action</option>
        <option value="Comedy" <%= "Comedy".equals(request.getAttribute("selectedCategory")) ? "selected" : "" %>>Comedy</option>
        <option value="Drama" <%= "Drama".equals(request.getAttribute("selectedCategory")) ? "selected" : "" %>>Drama</option>
        <option value="Horror" <%= "Horror".equals(request.getAttribute("selectedCategory")) ? "selected" : "" %>>Horror</option>
        <option value="Sci-Fi" <%= "Sci-Fi".equals(request.getAttribute("selectedCategory")) ? "selected" : "" %>>Sci-Fi</option>
    </select>
</form>



<div class='header'>
    <h1>Movie Library</h1>
        <%
            User user = (User) request.getAttribute("user");
            List<Movie> movies = (List<Movie>) request.getAttribute("movies");
            List<Movie> watchlistMovie = (List<Movie>) request.getAttribute("watchlist");
            %>
    <div class='user-info'>
        Logged in as <strong><%= user.getUsername()%>
    </strong> (Role: <%= user.getRole()%>)
    </div>
        <%
    if (movies.isEmpty()) {
        %>
    <div class='movie-card'>
        <h3>No movies available</h3>
        <p>There are no movies in the library yet.</p>
    </div>
        <% }
    else {
        for (Movie movie : movies) {
        %>

    <div class='movie-card'>
        <%
            if (movie.getProfilePictureBase64() != null) {
        %>
        <img src='data:image/jpeg;base64,<%=movie.getProfilePictureBase64()%>' alt='<%=movie.getTitle()%>'
             class='movie-poster'>
        <% } else {
        %>
        <div class='default-poster'>
            No Poster<br>Available
        </div>
        <%
            }
        %>
        <h2 class='movie-title'><%=movie.getTitle()%>
        </h2>

        <div class='movie-meta'>
            <strong><%=movie.getGenre()%>
            </strong>
            &nbsp;&bull;&nbsp;
            <%=(movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : "N/A")%>
        </div>

        <div class='movie-meta'>
            <span class='rating'>Rating: <%=movie.getRating()%>/10</span>
            <%
                if (movie.getDuration() != null) {
            %>
            &nbsp;&bull;&nbsp;
            Duration: <%=movie.getDuration() %> minutes
            <% }
            %>
        </div>

        <%
            if (movie.getDescription() != null && !movie.getDescription().isEmpty()) {
        %>
        <p class='movie-description'><%=movie.getDescription() %>
        </p>
        <%
            }
        %>


        <%
            if (watchlistMovie.contains(movie)) {
        %>
        <span class='added-to-watchlist'>✓ Added to Watchlist</span>
        <%
        } else {
        %>
        <form action='<%=request.getContextPath() + "/movie-user"%>' method='post' style='display: inline;'>
            <input type='hidden' name='movieId' value='<%=movie.getId()%>'>
            <button type='submit' class='btn btn-success'>Add to Watchlist</button>
        </form>
        <%
            }
        %>
        <a href="<%=request.getContextPath()%>/movie-details?movieId=<%=movie.getId()%>"
           class="btn btn-info" style="margin-left:10px;">View Details</a>


    </div>
    <hr class='hr-divider'>
        <%
        }
    }
    %>


</body>
</html>
