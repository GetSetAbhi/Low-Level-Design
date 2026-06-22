package com.demo.restaurant;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Restaurant restaurant =
                new Restaurant();

        restaurant.addTable(
                new Table(1, 2));

        restaurant.addTable(
                new Table(2, 4));

        restaurant.addTable(
                new Table(3, 6));

        ReservationService service =
                new ReservationService(
                        restaurant,
                        new FirstFitTableAllocationStrategy());
        
        // 7- 8 Pm
        LocalDateTime start = LocalDateTime.of(2026, 6, 5, 19, 00);
        LocalDateTime end = LocalDateTime.of(2026, 6, 5, 20, 00);

        Reservation r1 =
                service.reserve(
                        "John",
                        2, start, end);

        Reservation r2 =
                service.reserve(
                        "Alice",
                        4, start, end);

        System.out.println(
                "John assigned table "
                        + r1.getTable().getId());

        System.out.println(
                "Alice assigned table "
                        + r2.getTable().getId());

        service.cancel(
                r1.getReservationId());

        System.out.println(
                "Reservation cancelled");
    }
}
