package com.demo.safdssdgsdfgdfg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainBook {

	public static void main(String[] args) {
		SearchService searchService = new SearchService();

		Movie avengers = new Movie(1, "Avengers");
		Movie dhurandhar = new Movie(2, "Dhurandhar");

		Show show1 = new Show(1, avengers);
		Show show2 = new Show(2, dhurandhar);

		Show show3 = new Show(3, avengers);
		Show show4 = new Show(4, dhurandhar);

		searchService.addShowToCity("Chandigarh", show1);
		searchService.addShowToCity("Chandigarh", show2);
		searchService.addShowToCity("Jammu", show3);
		searchService.addShowToCity("Jammu", show4);

		List<Show> shows = searchService.getAllShowsInCity("Jammu");

		BookingService bookingService = new BookingService();
		Set<Integer> seatNumbers = new HashSet<>(List.of(1, 2));

		new Thread(() -> {
			bookingService.bookTickets("Abhishek", show4, seatNumbers);
		}).start();

		new Thread(() -> {
			bookingService.bookTickets("Palak", show4, seatNumbers);
		}).start();
	}

}

class BookingService {
	/**
	 * This follows a transactional type scenario.
	 * 1 acquire lock
	 * 2 validate
	 * 3 book it
	 * */
	public Booking bookTickets(String user, Show show, Set<Integer> seatNumbers) {
		List<Seat> seats = show.seats.stream().filter(i -> seatNumbers.contains(i.seatNumber))
				.sorted((a, b) -> Integer.compare(a.seatNumber, b.seatNumber)).toList();
		try {
			lockAllSeats(seats);
			for (Seat seat : seats) {
				if (seat.isBooked()) {
					throw new RuntimeException(seat.getSeatNumber() + " was already booked");
				}
			}
			for (Seat seat : seats) {
				seat.book();
			}
			System.out.println("All seats booked successfully for " + user);
			return new Booking(user, show, seats);
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			System.out.print("There was an error while booking seats");
			throw e;
		} finally {
			unlockAllSeats(seats);
		}
	}

	private void lockAllSeats(List<Seat> seats) {
		for (Seat seat : seats) {
			seat.lock();
		}
	}

	private void unlockAllSeats(List<Seat> seats) {
		int n = seats.size();
		for (int i = n - 1; i >= 0; i -= 1) {
			seats.get(i).unlock();
		}
	}
}

class Booking {
	String user;
	Show show;
	List<Seat> seats;

	public Booking(String user, Show show, List<Seat> seats) {
		super();
		this.user = user;
		this.show = show;
		this.seats = seats;
	}
}

class SearchService {
	Map<String, List<Show>> cityToShows;

	public SearchService() {
		this.cityToShows = new ConcurrentHashMap<>();
	}

	public void addShowToCity(String cityName, Show show) {
		List<Show> shows = this.cityToShows.computeIfAbsent(cityName, k -> new ArrayList<>());
		shows.add(show);
	}

	public List<Show> getAllShowsInCity(String city) {
		List<Show> shows = this.cityToShows.computeIfAbsent(city, k -> new ArrayList<>());
		shows.forEach(i -> {
			System.out.println(i.getShowId() + ", " + i.movie.name);
		});
		return shows;
	}
}

class Movie {
	int id;
	String name;

	public Movie(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}

class Seat {
	int seatNumber;
	boolean isBooked;
	private Lock lock;

	public Seat(int seatNumber) {
		super();
		this.seatNumber = seatNumber;
		this.isBooked = false;
		this.lock = new ReentrantLock();
	}

	public void book() {
		isBooked = true;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void lock() {
		this.lock.lock();
	}

	public void unlock() {
		this.lock.unlock();
	}
}

class Show {
	int showId;
	Movie movie;
	List<Seat> seats;

	public Show(int showId, Movie movie) {
		super();
		this.showId = showId;
		this.movie = movie;
		this.seats = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			this.seats.add(new Seat(i));
		}
	}

	public int getShowId() {
		return showId;
	}

	public Movie getMovie() {
		return movie;
	}

	public List<Seat> getSeats() {
		return seats;
	}
}
