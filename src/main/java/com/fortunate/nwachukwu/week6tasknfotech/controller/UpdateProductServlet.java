package com.fortunate.nwachukwu.week6tasknfotech.controller;

import com.fortunate.nwachukwu.week6tasknfotech.connection.DBConnector;
import com.fortunate.nwachukwu.week6tasknfotech.dao.ProductDao;
import com.fortunate.nwachukwu.week6tasknfotech.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateProduct", value = "/UpdateProduct")
public class UpdateProductServlet extends HttpServlet {
    ProductDao productDao = new ProductDao(DBConnector.getConnection());
    int id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = Integer.parseInt(request.getParameter("id"));
        Product product =  productDao.getSingleProduct(id);
        request.setAttribute("name",product.getName());
        request.setAttribute("category",product.getCategory());
        request.setAttribute("price",product.getPrice());
        request.setAttribute("quantity",product.getQuantity());

        RequestDispatcher rp = request.getRequestDispatcher("updateProduct.jsp");
        rp.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setCategory(category);
        product.setId(id);
        try {
            if (productDao.updateProduct(product)){
                request.setAttribute("UpdateSuccess", "Product updated Successfully");
                request.getRequestDispatcher("allProduct.jsp").forward(request,response);
            }
        }
        catch (Exception e){
            e.getMessage();
        }

    }
}
