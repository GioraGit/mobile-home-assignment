package com.giora.climasale.features.weatherDetails.data;

import com.giora.climasale.features.weatherDetails.domain.UnitSystem;

public interface IPrecipitationConverter {
	Double convertPrecipitation(Double value, UnitSystem targetUnitSystem);
}
