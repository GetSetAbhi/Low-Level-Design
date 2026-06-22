package com.demo.restaurant;

public class Table {

    private final int id;
    private final int capacity;

    public Table(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }
}