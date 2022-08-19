package com.fortunate.nwachukwu.week6tasknfotech.controller;

import com.fortunate.nwachukwu.week6tasknfotech.connection.DBConnector;
import com.fortunate.nwachukwu.week6tasknfotech.dao.UserDao;
import com.fortunate.nwachukwu.week6tasknfotech.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        RequestDispatcher dispatcher;

        if (email == null || email.equals("")) {
            request.setAttribute("status", "invalidEmail");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
        if (password == null || password.equals("")) {
            request.setAttribute("status", "invalidPassword");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
        UserDao userDao = new UserDao(DBConnector.getConnection());
        User user = userDao.userLogin(email, password);

        try (PrintWriter out = response.getWriter()) {
            if ("fortunenwachukwu@gmail.com".equals(email) && "perp".equals(password)) {
                session.setAttribute("username", email);
                dispatcher = request.getRequestDispatcher("adminMainPage.jsp");
                dispatcher.forward(request, response);

            } else if (user != null) {
                session.setAttribute("auth", user);
                session.setAttribute("Email",email);

                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}