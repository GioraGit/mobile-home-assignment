package com.giora.climasale.features.weatherDetails.data;

import com.giora.climasale.features.weatherDetails.domain.ForecastProperty;

public interface IForecastsFieldsParameterBuilder {
	String build(ForecastProperty[] forecastProperties);
}
