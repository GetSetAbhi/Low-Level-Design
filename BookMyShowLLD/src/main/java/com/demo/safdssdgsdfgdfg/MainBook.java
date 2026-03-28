package com.demo.safdssdgsdfgdfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
		Set<Integer> seatNumbers = new HashSet<>(Arrays.asList(1, 2));
		bookingService.bookTickets("Abhishek", show4, seatNumbers);
		bookingService.bookTickets("Palak", show4, seatNumbers);
	}

}

class BookingService {
	public Booking bookTickets(String user, Show show, Set<Integer> seatNumbers) {
		List<Seat> seats = show.seats.stream().filter(i -> seatNumbers.contains(i.seatNumber))
				.sorted((a, b) -> Integer.compare(a.seatNumber, b.seatNumber)).collect(Collectors.toList());
		try {
			for (Seat seat : seats) {
				synchronized (seat) {
					seat.book();
				}
			}
			System.out.println("All seats booked successfully for " + user);
			return new Booking(user, show, seats);
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			System.out.print("There was an error while booking seats");
			throw e;
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

	public Seat(int seatNumber) {
		super();
		this.seatNumber = seatNumber;
		this.isBooked = false;
	}

	public void book() {
		if (isBooked) {
			throw new RuntimeException("Seat is already booked");
		}
		isBooked = true;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public boolean isBooked() {
		return isBooked;
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