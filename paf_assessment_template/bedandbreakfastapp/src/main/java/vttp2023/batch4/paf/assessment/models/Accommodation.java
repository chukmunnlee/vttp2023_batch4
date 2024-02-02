package vttp2023.batch4.paf.assessment.models;

import java.util.List;
import java.util.LinkedList;

// IMPORTANT: DO NOT MODIFY THIS CLASS
// If this class is changed, any assessment task relying on this class will
// not be marked

public class Accommodation {
	private String id; // _id
	private String name; // name
	private String summary; // summary
	private int minNights; // minimum_nights
	private int maxNights; // maximum_nights
	private int accomodates; // accomates
	private float price; // price
	private String street; // address.street
	private String suburb; // address.suburb
	private String country; // address.country
	private List<String> amenities = new LinkedList<>(); // amenities
	private String image; // images.picture.url

	public void setId(String id) { this.id = id; }
	public String getId() { return this.id; }

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setSummary(String summary) { this.summary = summary; }
	public String getSummary() { return this.summary; }

	public void setStreet(String street) { this.street = street; }
	public String getStreet() { return this.street; }

	public void setSuburb(String suburb) { this.suburb = suburb; }
	public String getSuburb() { return this.suburb; }

	public void setCountry(String country) { this.country = country; }
	public String getCountry() { return this.country; }

	public void setImage(String image) { this.image = image; }
	public String getImage() { return this.image; }

	public void setMinNights(int minNights) { this.minNights = minNights; }
	public int getMinNights() { return this.minNights; }

	public void setMaxNights(int maxNights) { this.maxNights = maxNights; }
	public int getMaxNights() { return this.maxNights; }

	public void setAccomodates(int accomodates) { this.accomodates = accomodates; }
	public int getAccomodates() { return this.accomodates; }

	public void setPrice(float price) { this.price = price; }
	public float getPrice() { return this.price; }

	public void setAmenities(List<String> amenities) { this.amenities = amenities; }
	public List<String> getAmenities() { return this.amenities; }
}
