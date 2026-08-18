package com.jmxlab.application.order;

import com.jmxlab.domain.order.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderProcessorTest {

    @Test
    void shouldIncrementProcessedOrderCount() {
        OrderProcessor processor = new OrderProcessor();

        processor.process(new Order("order-1"));
        processor.process(new Order("order-2"));

        assertEquals(2, processor.processedOrders());
    }
}
