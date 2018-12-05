package com.giora.climasale.features.capitalList.presentation;

public class CapitalViewModel {
	String city = "";
	String country = "";
	String flagImageUrl = "";
	double lat;
	double lng;

	public String getCity() {
		return city.isEmpty() ? "--- No capital city ---" : city;
	}

	public String getCountry() {
		return country.isEmpty() ? "--- Standalone capital ---" : country;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public String getMarkerText() {
		return "Somewhere in " + country;
	}
}
