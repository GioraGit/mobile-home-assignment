package com.giora.climasale.features.weatherDetails.data;

import com.giora.climasale.features.weatherDetails.domain.ForecastProperty;

public class ForecastsFieldsParameterBuilder implements IForecastsFieldsParameterBuilder {
	@Override
	public String build(ForecastProperty[] forecastProperties) {
		StringBuilder stringBuilder = new StringBuilder(map(forecastProperties[0]));
		for (int forecastPropertyIndex = 1; forecastPropertyIndex < forecastProperties.length; ++forecastPropertyIndex) {
			stringBuilder.append(',');
			stringBuilder.append(map(forecastProperties[forecastPropertyIndex]));
		}
		return stringBuilder.toString();
	}

	private String map(ForecastProperty forecastProperty) {
		switch (forecastProperty) {
			case Temperature:
				return "temp";
			case Precipitation:
				return "precipitation";
			default:
				return "";
		}
	}
}
