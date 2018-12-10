package com.giora.climasale.features.capitalList.domain;

public class Capital {

	public final String city;
	public final String country;
	public final String flagImageUrl;
	public final Double lat;
	public final Double lng;

	public Capital(String city,
				   String country,
				   String flagImageUrl,
				   Double lat,
				   Double lng) {
		this.city = city;
		this.country = country;
		this.flagImageUrl = flagImageUrl;
		this.lat = lat;
		this.lng = lng;
	}
}
