package com.jmxlab.application.order;

import com.jmxlab.domain.order.Order;

import java.util.Objects;

public class OrderProcessor {

    private long processedOrders;

    public void process(Order order) {
        Objects.requireNonNull(order, "order must not be null");

        processedOrders++;
    }

    public long processedOrders() {
        return processedOrders;
    }
}
