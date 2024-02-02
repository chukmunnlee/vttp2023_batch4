package vttp2023.batch4.paf.assessment.models;

// IMPORTANT: DO NOT MODIFY THIS CLASS
// If this class is changed, any assessment task relying on this class will
// not be marked

public class AccommodationSummary {
	private String id; // _id
	private String name; // name
	private int accomodates; // accommodates
	private float price; // price

	public void setId(String id) { this.id = id; }
	public String getId() { return this.id; }

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setAccomodates(int accomodates) { this.accomodates = accomodates; }
	public int getAccomodates() { return this.accomodates; }

	public void setPrice(float price) { this.price = price; }
	public float getPrice() { return this.price; }
}
