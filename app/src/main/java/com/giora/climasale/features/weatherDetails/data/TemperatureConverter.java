package com.giora.climasale.features.weatherDetails.data;

import com.giora.climasale.features.weatherDetails.domain.UnitSystem;

public class TemperatureConverter implements ITemperatureConverter {
	@Override
	public Double convertTemperature(Double value, UnitSystem targetUnitSystem) {
		if (value == null)
			return null;

		switch (targetUnitSystem) {
			case Metric:
				return convertFahrenheitToCelsius(value);
			case Imperial:
				return convertCelsiusToFahrenheit(value);
			default:
				return null;
		}
	}

	private Double convertFahrenheitToCelsius(Double value) {
		return (value-32)*5/9;
	}

	private Double convertCelsiusToFahrenheit(Double value) {
		return (value*9/5) + 32;
	}
}
