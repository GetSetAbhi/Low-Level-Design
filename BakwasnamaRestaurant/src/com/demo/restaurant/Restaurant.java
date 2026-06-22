package com.demo.restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {

    private final List<Table> tables;
    private final Map<String, Reservation> reservations;

    public Restaurant() {
        this.tables = new ArrayList<>();
        this.reservations = new HashMap<>();
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public List<Table> getTables() {
        return tables;
    }

    public Map<String, Reservation> getReservations() {
        return reservations;
    }
}
