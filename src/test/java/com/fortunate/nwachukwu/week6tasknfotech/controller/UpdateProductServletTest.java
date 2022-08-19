package com.fortunate.nwachukwu.week6tasknfotech.controller;

import com.fortunate.nwachukwu.week6tasknfotech.dao.ProductDao;
import com.fortunate.nwachukwu.week6tasknfotech.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateProductServletTest extends Mockito {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    UpdateProductServlet updateProductServlet = new UpdateProductServlet();
    ProductDao productDao = mock(ProductDao.class);

    @Test
    @DisplayName(
            "Should set the name, category, price and quantity of the product to the request when the product is found")
    void doGetWhenProductIsFoundThenSetNameCategoryPriceAndQuantityToRequest() throws ServletException, IOException {

        Product product = new Product(1, "Modyle Gold Wedding Rings", "Ring", 105.0, 100, "Modyle Gold Wedding Rings.webp");
        when(request.getParameter("id")).thenReturn("1");
        when(productDao.getSingleProduct(1)).thenReturn(product);
        updateProductServlet.productDao = productDao;

        updateProductServlet.doGet(request, response);

        verify(request).setAttribute("Modyle Gold Wedding Rings", "name");
        verify(request).setAttribute("Ring", "category");
        verify(request).setAttribute("price", 105.0);
        verify(request).setAttribute("quantity", 100);
    }

    @Test
    @DisplayName("Should forward to updateProduct.jsp when the product is found")
    void doGetWhenProductIsFoundThenForwardToUpdateProductJsp() throws ServletException, IOException {

        updateProductServlet.doGet(request, response);

        verify(request).getRequestDispatcher("updateProduct.jsp");
    }

    @Test
    @DisplayName("Should update the product when the product is valid")
    void doPostWhenProductIsValidThenUpdateProduct() throws ServletException, IOException {
        updateProductServlet.productDao = productDao;
        Product product = new Product();
        product.setId(1);
        product.setName("test");
        product.setCategory("test");
        product.setPrice(1.0);
        product.setQuantity(1);

        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("category")).thenReturn("test");
        when(request.getParameter("price")).thenReturn("1.0");
        when(request.getParameter("quantity")).thenReturn("1");

        when(productDao.updateProduct(product)).thenReturn(true);

        updateProductServlet.doPost(request, response);

        verify(productDao).updateProduct(product);
    }

    @Test
    @DisplayName("Should not update the product when the product is invalid")
    void doPostWhenProductIsInvalidThenNotUpdateProduct() {
        UpdateProductServlet updateProductServlet = new UpdateProductServlet();
        updateProductServlet.productDao = productDao;

        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("category")).thenReturn("");
        when(request.getParameter("quantity")).thenReturn("");
        when(request.getParameter("price")).thenReturn("");

        assertThrows(Exception.class, () -> updateProductServlet.doPost(request, response));
    }
}