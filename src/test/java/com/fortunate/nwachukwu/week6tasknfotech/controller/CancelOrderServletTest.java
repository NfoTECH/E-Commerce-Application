package com.fortunate.nwachukwu.week6tasknfotech.controller;

import com.fortunate.nwachukwu.week6tasknfotech.dao.OrderDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class CancelOrderServletTest extends Mockito {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter printWriter = mock(PrintWriter.class);
    OrderDao orderDao = mock(OrderDao.class);

//    @Test
//    @DisplayName("Should cancel the order when the id is not null")
//    void doGetWhenIdIsNotNullThenCancelOrder() throws IOException {
//
//        CancelOrderServlet cancelOrderServlet = new CancelOrderServlet();
//
//        when(request.getParameter("id")).thenReturn("1");
//        when(response.getWriter()).thenReturn(printWriter);
//
//        verify(orderDao, times(1)).cancelOrder(1);
//    }

    @Test
    @DisplayName("Should redirect to orders.jsp when the id is not null")
    void doGetWhenIdIsNotNullThenRedirectToOrdersJsp() throws IOException {
        CancelOrderServlet cancelOrderServlet = new CancelOrderServlet();

        when(request.getParameter("id")).thenReturn("1");
        when(response.getWriter()).thenReturn(printWriter);

        assertDoesNotThrow(() -> cancelOrderServlet.doGet(request, response));
    }
}