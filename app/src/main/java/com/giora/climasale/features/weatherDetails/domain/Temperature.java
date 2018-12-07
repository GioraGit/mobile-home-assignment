package com.giora.climasale.features.weatherDetails.domain;

public class Temperature {

	private final double temperature;
	private final TemperatureUnits temperatureUnits;

	public Temperature(double temperature, TemperatureUnits temperatureUnits) {
		this.temperature = temperature;
		this.temperatureUnits = temperatureUnits;
	}

	public double getTemperature() {
		return temperature;
	}

	public TemperatureUnits getTemperatureUnits() {
		return temperatureUnits;
	}
}
