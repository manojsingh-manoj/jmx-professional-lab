package com.jmxlab.domain.order;

import java.util.Objects;

public final class Order {

    private final String id;

    public Order(String id) {
        this.id = Objects.requireNonNull(id, "id must not be null");
    }

    public String id() {
        return id;
    }
}
