package com.giora.climasale.features.weatherDetails.domain;

import java.util.Date;

public class Forecast {

	private final Date date;
	private final UnitSystem unitSystem;
	private final Double minTemperature;
	private final Double maxTemperature;
	private final Double precipitation;

	public Forecast(Date date,
					UnitSystem unitSystem,
					Double minTemperature,
					Double maxTemperature,
					Double precipitation) {
		this.date = date;
		this.unitSystem = unitSystem;
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
		this.precipitation = precipitation;
	}

	public Date getDate() {
		return date;
	}

	public Double getMinTemperature() {
		return minTemperature;
	}

	public Double getMaxTemperature() {
		return maxTemperature;
	}

	public Double getPrecipitation() {
		return precipitation;
	}

	public UnitSystem getUnitSystem() {
		return unitSystem;
	}
}
