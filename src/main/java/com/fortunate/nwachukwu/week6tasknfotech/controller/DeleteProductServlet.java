package com.fortunate.nwachukwu.week6tasknfotech.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.fortunate.nwachukwu.week6tasknfotech.connection.DBConnector;
import com.fortunate.nwachukwu.week6tasknfotech.dao.ProductDao;

@WebServlet(name = "DeleteProduct", value = "/DeleteProduct")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductDao dao = new ProductDao(DBConnector.getConnection());
        if (dao.deleteProducts(id)){
            RequestDispatcher rp = request.getRequestDispatcher("allProduct.jsp");
            rp.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

