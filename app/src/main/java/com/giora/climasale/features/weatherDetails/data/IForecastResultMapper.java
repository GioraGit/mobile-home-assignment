package com.giora.climasale.features.weatherDetails.data;

import com.giora.climasale.features.weatherDetails.domain.Forecast;

public interface IForecastResultMapper {
	Forecast map(ForecastResult forecastResult);
}
