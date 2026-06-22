package com.demo.seats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) {
		BookingService service = new BookingService();

		new Thread(() -> {
			try {
				service.bookSeats("Thread 1", 1, 2, 3);
			} catch (Exception e) {
				System.out.println("Thread 1 : " + e.getMessage());
				System.err.println("Could not book seats");
			}
		}).start();

		new Thread(() -> {
			try {
				service.bookSeats("Thread 2", 1, 2, 3);
			} catch (Exception e) {
				System.err.println("Thread 2 : " + e.getMessage());
				System.err.println("Could not book seats");
			}
		}).start();
	}

}

class BookingService {
	private Map<Integer, Seat> seatMap;

	public BookingService() {
		this.seatMap = new ConcurrentHashMap<>();
		for (int i = 0; i < 10; i++) {
			this.seatMap.put(i + 1, new Seat(i + 1));
		}
	}

	public void bookSeats(String name, int... seats) {
		List<Seat> seatList = new ArrayList<>();
		Arrays.sort(seats);
		try {
			for (int i : seats) {
				seatList.add(this.seatMap.get(i));
			}
			
			lockAll(seatList);
			
			for (Seat seat : seatList) {
				if (seat.isBooked()) {
					throw new IllegalStateException(seat.getSeatNo() + " is already booked");
				}
			}

			for (Seat seat : seatList) {
				seat.bookSeat();
				System.out.println(name + " booked seat " + seat.getSeatNo());
			}
		} finally {
			unlockAll(seatList);
		}
	}

	private void lockAll(List<Seat> seats) {
		for (Seat seat : seats) {
			seat.lock();
		}
	}

	private void unlockAll(List<Seat> seats) {
		int n = seats.size();
		for (int i = n - 1; i >= 0; i --) {
			seats.get(i).unlock();
		}
	}
}

class Seat {
	private int seatNo;
	private volatile boolean isBooked;
	private Lock lock;

	public Seat(int seatNo) {
		this.seatNo = seatNo;
		this.isBooked = false;
		this.lock = new ReentrantLock();
	}

	public int getSeatNo() {
		return seatNo;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void bookSeat() {
		isBooked = true;
	}

	public void lock() {
		this.lock.lock();
	}
	
	public void unlock() {
		this.lock.unlock();
	}
}
