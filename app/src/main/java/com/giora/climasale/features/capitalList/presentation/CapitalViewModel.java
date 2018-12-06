package com.giora.climasale.features.capitalList.presentation;

public class CapitalViewModel {

	private static final String NO_CAPITAL_CITY_TEXT = "--- No capital city ---";

	String city = "";
	String country = "";
	String flagImageUrl = "";

	public String getCity() {
		return city.isEmpty() ? NO_CAPITAL_CITY_TEXT : city;
	}

	public String getMarkerAddress() {
		return city.isEmpty() ? country : city;
	}
}
