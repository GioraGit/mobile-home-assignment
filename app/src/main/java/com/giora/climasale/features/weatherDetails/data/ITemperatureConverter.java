package com.giora.climasale.features.weatherDetails.data;

import com.giora.climasale.features.weatherDetails.domain.UnitSystem;

public interface ITemperatureConverter {
	Double convertTemperature(Double value, UnitSystem targetUnitSystem);
}
