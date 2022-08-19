package com.fortunate.nwachukwu.week6tasknfotech.controller;

import com.fortunate.nwachukwu.week6tasknfotech.model.Cart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuantityIncDecServletTest extends Mockito {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    QuantityIncDecServlet quantityIncDecServlet = new QuantityIncDecServlet();

    @Test
    @DisplayName("Should increase the quantity of the product when action is inc")
    void doGetWhenActionIsIncThenIncreaseQuantity() throws ServletException, IOException {

        when(request.getParameter("action")).thenReturn("inc");
        when(request.getParameter("id")).thenReturn("1");
        ArrayList<Cart> cart_list = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(1);
        cart.setQuantity(1);
        cart_list.add(cart);
        when(request.getSession().getAttribute("cart-list")).thenReturn(cart_list);

        quantityIncDecServlet.doGet(request, response);

        assertEquals(2, cart_list.get(0).getQuantity());
    }

    @Test
    @DisplayName("Should decrease the quantity of the product when action is dec and quantity > 1")
    void doGetWhenActionIsDecAndQuantityGreaterThanOneThenDecreaseQuantity() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("dec");
        when(request.getParameter("id")).thenReturn("1");
        ArrayList<Cart> cart_list = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(1);
        cart.setQuantity(2);
        cart_list.add(cart);
        when(request.getSession().getAttribute("cart-list")).thenReturn(cart_list);

        quantityIncDecServlet.doGet(request, response);

        assertEquals(1, cart_list.get(0).getQuantity());
    }
}