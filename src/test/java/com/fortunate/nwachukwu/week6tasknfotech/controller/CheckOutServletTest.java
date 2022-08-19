package com.fortunate.nwachukwu.week6tasknfotech.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class CheckOutServletTest extends Mockito {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    PrintWriter writer = mock(PrintWriter.class);

    @Test
    @DisplayName("Should redirect to login.jsp when the user is not logged in")
    void doGetWhenUserIsNotLoggedInThenRedirectToLoginJsp() throws IOException {


        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("auth")).thenReturn(null);
        when(response.getWriter()).thenReturn(writer);

        CheckOutServlet servlet = new CheckOutServlet();
        try {
            servlet.doGet(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            fail("Should not throw exception");
        }

        verify(response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(response).setHeader("Location", "login.jsp");
    }

    @Test
    @DisplayName("Should redirect to cart.jsp when the cart list is null")
    void doGetWhenCartListIsNullThenRedirectToCartJsp() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("cart-list")).thenReturn(null);

        CheckOutServlet checkOutServlet = new CheckOutServlet();
        try {
            checkOutServlet.doGet(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

        verify(response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
    }
}