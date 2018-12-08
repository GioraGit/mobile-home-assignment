package com.giora.climasale.features.weatherDetails.data;

import com.giora.climasale.features.weatherDetails.domain.Forecast;
import com.giora.climasale.features.weatherDetails.domain.UnitSystem;

public interface IForecastResultMapper {
	Forecast map(ForecastResult forecastResult, UnitSystem unitSystem);
}
