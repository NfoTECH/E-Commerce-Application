package com.fortunate.nwachukwu.week6tasknfotech.controller;

import com.fortunate.nwachukwu.week6tasknfotech.model.Cart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AddToCartServletTest extends Mockito {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);

    @Test
    @DisplayName("Should add the item to the cart when the item is not in the cart")
    void doGetWhenItemIsNotInCartThenAddToCart() throws ServletException, IOException {

        when(request.getParameter("id")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("cart-list")).thenReturn(null);

        AddToCartServlet addToCartServlet = new AddToCartServlet();
        addToCartServlet.doGet(request, response);

        verify(session).setAttribute("cart-list", any());
    }

    @Test
    @DisplayName("Should not add the item to the cart when the item is already in the cart")
    void doGetWhenItemIsAlreadyInCartThenNotAddToCart() {
        ArrayList<Cart> cartList = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(1);
        cart.setQuantity(1);
        cartList.add(cart);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("cart-list")).thenReturn(cartList);

        AddToCartServlet addToCartServlet = new AddToCartServlet();

        assertThrows(IOException.class, () -> addToCartServlet.doGet(request, response));
    }
}