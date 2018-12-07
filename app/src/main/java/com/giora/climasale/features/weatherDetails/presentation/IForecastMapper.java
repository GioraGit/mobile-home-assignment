package com.giora.climasale.features.weatherDetails.presentation;

import com.giora.climasale.features.weatherDetails.domain.Forecast;

public interface IForecastMapper {
	ForecastViewModel map(Forecast forecast);
}
