package com.giora.climasale.features.weatherDetails.domain;

public class Precipitation {

	private final double precipitation;
	private final PrecipitationUnits precipitationUnits;

	public Precipitation(double precipitation, PrecipitationUnits precipitationUnits) {
		this.precipitation = precipitation;
		this.precipitationUnits = precipitationUnits;
	}

	public double getPrecipitation() {
		return precipitation;
	}

	public PrecipitationUnits getPrecipitationUnits() {
		return precipitationUnits;
	}
}
