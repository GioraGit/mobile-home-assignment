package com.giora.climasale.features.capitalList.presentation;

public class CapitalViewModel {

	private static final String NO_CAPITAL_CITY_TEXT = "--- No capital city ---";

	String city = "";
	String country = "";
	String flagImageUrl = "";
	Double latitude;
	Double longitude;

	public String getCity() {
		return city.isEmpty() ? NO_CAPITAL_CITY_TEXT : city;
	}

	public String getMarkerAddress() {
		return city.isEmpty() ? country : String.format("%s, %s", city, country);
	}

	public String getTitle() {
		return city.isEmpty() ? country : city;
	}

	public String getCountry() {
		return country;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}
}
