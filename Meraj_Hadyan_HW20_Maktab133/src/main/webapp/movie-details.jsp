<%@ page import="ir.maktabsharif.model.Movie" %>
<%@ page import="ir.maktabsharif.model.Review" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie Details</title>
    <link rel="stylesheet" href="css/movie-details.css">
</head>
<body>
<%
    Movie movie = (Movie) request.getAttribute("movie");
    List<Review> reviews = (List<Review>) request.getAttribute("reviews");
    Double averageRating = (Double) request.getAttribute("averageRating");
%>

<h1><%= movie.getTitle() %></h1>
<p><strong>Genre:</strong> <%= movie.getGenre() %></p>
<p><strong>Release:</strong> <%= movie.getReleaseDate() %></p>
<p><strong>Overall Rating:</strong> <%= averageRating > 0 ? averageRating : "No ratings yet" %>/5</p>

<hr>

<h3>Rate This Movie</h3>
<form action="<%= request.getContextPath() %>/movie-details" method="post">
    <input type="hidden" name="movieId" value="<%= movie.getId() %>">

    <label>Rating (1–5):</label>
    <select name="rating" required>
        <option value="1">1 ⭐</option>
        <option value="2">2 ⭐⭐</option>
        <option value="3">3 ⭐⭐⭐</option>
        <option value="4">4 ⭐⭐⭐⭐</option>
        <option value="5">5 ⭐⭐⭐⭐⭐</option>
    </select>

    <br><br>

    <label>Comment:</label><br>
    <textarea name="comment" rows="3" cols="40" placeholder="Write your thoughts..." required></textarea>

    <br><br>
    <button type="submit">Submit Review</button>
</form>

<hr>

<h3>User Reviews</h3>
<%
    if (reviews == null || reviews.isEmpty()) {
%>
<p>No reviews yet.</p>
<%
} else {
    for (Review r : reviews) {
%>
<div style="border:1px solid #ccc; padding:10px; margin:10px 0;">
    <strong><%= r.getUser().getUsername() %></strong> — Rated <%= r.getRating() %>/5
    <p><%= r.getComment() %></p>
</div>
<%
        }
    }
%>
</body>
</html>
