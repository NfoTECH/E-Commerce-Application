package com.fortunate.nwachukwu.week6tasknfotech.controller;

import com.fortunate.nwachukwu.week6tasknfotech.connection.DBConnector;
import com.fortunate.nwachukwu.week6tasknfotech.dao.ProductDao;
import com.fortunate.nwachukwu.week6tasknfotech.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class AddProductServletTest extends Mockito {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    ProductDao productDao = mock(ProductDao.class);

    @Test
    @DisplayName("Should redirect to addproduct.jsp when the method is called")
    void doGetShouldRedirectToAddProductJspWhenTheMethodIsCalled() throws ServletException, IOException {
        AddProductServlet addProductServlet = new AddProductServlet();
        addProductServlet.doGet(request, response);
        verify(response).sendRedirect("addProduct.jsp");
    }

    @Test
    @DisplayName("Should redirect to addproduct.jsp when the request method is get")
    void doPostWhenRequestMethodIsGetThenRedirectToAddProductJsp() throws ServletException, IOException {

        AddProductServlet addProductServlet = new AddProductServlet();

        addProductServlet.doGet(request, response);

        verify(response).sendRedirect("addProduct.jsp");
    }
}