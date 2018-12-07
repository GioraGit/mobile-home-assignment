package com.giora.climasale.features.weatherDetails.domain;

class Temperature {

	private final double temperature;
	private final TemperatureUnits temperatureUnits;

	Temperature(double temperature, TemperatureUnits temperatureUnits) {
		this.temperature = temperature;
		this.temperatureUnits = temperatureUnits;
	}
}
