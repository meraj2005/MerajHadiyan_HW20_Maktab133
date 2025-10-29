package ir.maktabsharif.servlet;

import ir.maktabsharif.enums.Role;
import ir.maktabsharif.model.*;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.repository.impl.*;
import ir.maktabsharif.service.*;
import ir.maktabsharif.service.impl.*;
import ir.maktabsharif.util.PasswordUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        password = PasswordUtil.hashPassword(password);
        userService.save(User.builder().username(username).email(email).password(password).role(Role.USER).build());
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}