package com.giora.climasale.features.weatherDetails.domain;

import java.util.Date;

public class Forecast {

	private final Date date;
	private final Temperature minTemperature;
	private final Temperature maxTemperature;
	private final Precipitation precipitation;

	public Forecast(Date date,
			 Temperature minTemperature,
			 Temperature maxTemperature,
			 Precipitation precipitation) {
		this.date = date;
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
		this.precipitation = precipitation;
	}

	public Date getDate() {
		return date;
	}

	public Temperature getMinTemperature() {
		return minTemperature;
	}

	public Temperature getMaxTemperature() {
		return maxTemperature;
	}

	public Precipitation getPrecipitation() {
		return precipitation;
	}
}
