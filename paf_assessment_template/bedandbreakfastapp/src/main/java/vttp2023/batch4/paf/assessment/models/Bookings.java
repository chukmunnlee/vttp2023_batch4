package vttp2023.batch4.paf.assessment.models;

import java.util.UUID;

// IMPORTANT: DO NOT MODIFY THIS CLASS
// If this class is changed, any assessment task relying on this class will
// not be marked
public class Bookings {

	private final String bookingId;
	private String listingId;
	private String name;
	private String email;
	private int duration;

	public Bookings() {
		this(UUID.randomUUID().toString().substring(0, 8));
	}

	public Bookings(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingId() { return this.bookingId; }

	public void setListingId(String listingId) { this.listingId = listingId; }
	public String getListingId() { return this.listingId; }

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return this.email; }

	public void setDuration(int duration) { this.duration = duration; }
	public int getDuration() { return this.duration; }

}
