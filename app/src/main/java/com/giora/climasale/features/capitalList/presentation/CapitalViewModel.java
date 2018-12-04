package com.giora.climasale.features.capitalList.presentation;

class CapitalViewModel {
	String city = "";
	String country = "";

	public String getCity() {
		return city.isEmpty() ? "--- No capital city ---" : city;
	}

	public String getCountry() {
		return country.isEmpty() ? "--- Standalone capital ---" : country;
	}
}
