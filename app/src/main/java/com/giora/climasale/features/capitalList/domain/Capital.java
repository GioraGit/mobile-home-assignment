package com.giora.climasale.features.capitalList.domain;

public class Capital {

	public final String city;
	public final String country;
	public final String flagImageUrl;

	public Capital(String city,
				   String country,
				   String flagImageUrl) {
		this.city = city;
		this.country = country;
		this.flagImageUrl = flagImageUrl;
	}
}
