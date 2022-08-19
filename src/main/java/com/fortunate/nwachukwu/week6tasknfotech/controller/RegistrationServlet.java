package com.fortunate.nwachukwu.week6tasknfotech.controller;

import com.fortunate.nwachukwu.week6tasknfotech.connection.DBConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

 @WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("name");
        String password = request.getParameter("pass");
        String re_pass = request.getParameter("re_pass");
        String email = request.getParameter("email");
        String telephone = request.getParameter("contact");
        RequestDispatcher dispatcher;
        Connection connection = null;


        if (username == null || username.equals("")) {
            request.setAttribute("status", "invalidUsername");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        if (password == null || password.equals("")) {
            request.setAttribute("status", "invalidPassword");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        } else if(!password.equals(re_pass)) {
            request.setAttribute("status", "invalidRePassword");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        if (email == null || email.equals("")) {
            request.setAttribute("status", "invalidEmail");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        if (telephone == null || telephone.equals("")) {
            request.setAttribute("status", "invalidPhoneNo");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }


        PrintWriter out = response.getWriter();
        out.print(username);
        out.print(password);
        out.print(email);
        out.print(telephone);


        try{
            PreparedStatement statement = DBConnector.getConnection()
                    .prepareStatement("INSERT INTO users(username,password,email,telephone) VALUES (?,?,?,?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, telephone);

            int rowCount = statement.executeUpdate();
            dispatcher = request.getRequestDispatcher("registration.jsp");
            if (rowCount > 0) {
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
