package vttp.ssf.revision.newsapi;

import java.util.stream.Collectors;
import java.util.Arrays;

public class Utils {

	public static final String[] COUNTRY_CODE = {
		"ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn", 
		"co", "cu", "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", 
		"id", "ie", "il", "in", "it", "jp", "kr", "lt", "lv", "ma", 
		"mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro",
		"rs", "ru", "sa", "se", "sg", "si", "sk", "th", "tr", "tw", 
		"ua", "us", "ve", "za"
	};

	public static final String[] CATEGORY = { 
		"business", "entertainment", "general", 
		"health", "science", "sports", "technology" 
	};

	public static String getCodeAsCSV() {
		return Arrays.asList(COUNTRY_CODE).stream().collect(Collectors.joining(","));
	}
}
