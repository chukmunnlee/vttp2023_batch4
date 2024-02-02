package vttp2023.batch4.paf.assessment;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import vttp2023.batch4.paf.assessment.models.Accommodation;

public class Utils {

	public static Accommodation toAccommodation(Document doc) {
		Accommodation acc = new Accommodation();
		acc.setId(doc.getString("_id"));
		acc.setName(doc.getString("name"));
		acc.setSummary(doc.getString("summary"));
		acc.setMinNights(doc.getInteger("min_nights"));
		acc.setMaxNights(doc.getInteger("max_nights"));
		acc.setAccomodates(doc.getInteger("accommodates"));
		acc.setPrice(doc.get("price", Number.class).floatValue());
		Document _doc = doc.get("address", Document.class);
		acc.setStreet(_doc.getString("street"));
		acc.setSuburb(_doc.getString("suburb"));
		acc.setCountry(_doc.getString("country"));
		acc.setAmenities(doc.getList("amenities", String.class));
		_doc = doc.get("images", Document.class);
		acc.setImage(_doc.getString("picture_url"));
		return acc;
	}

	public static JsonObject toJson(Accommodation acc) {
		JsonArray amenities = Json.createArrayBuilder(acc.getAmenities()).build();
		return Json.createObjectBuilder()
			.add("id", acc.getId())
			.add("name", acc.getName())
			.add("summary", acc.getSummary())
			.add("min_nights", acc.getMinNights())
			.add("max_nights", acc.getMaxNights())
			.add("price", acc.getPrice())
			.add("street", acc.getStreet())
			.add("suburb", acc.getSuburb())
			.add("country", acc.getCountry())
			.add("amenities", amenities)
			.add("image", acc.getImage())
			.build();
	}
}
