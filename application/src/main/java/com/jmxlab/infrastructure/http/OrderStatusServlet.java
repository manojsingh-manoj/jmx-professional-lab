package com.jmxlab.infrastructure.http;

import com.jmxlab.application.order.OrderProcessor;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/orders/status")
public class OrderStatusServlet extends HttpServlet {

    private final OrderProcessor orderProcessor = new OrderProcessor();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        response.getWriter().println(
                "processedOrders=" + orderProcessor.processedOrders()
        );
    }
}