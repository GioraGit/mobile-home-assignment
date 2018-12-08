package com.giora.climasale.features.weatherDetails.data;

import com.giora.climasale.features.weatherDetails.domain.UnitSystem;

public class PrecipitationConverter implements IPrecipitationConverter {
	@Override
	public Double convertPrecipitation(Double value, UnitSystem targetUnitSystem) {
		if (value == null)
			return null;

		switch (targetUnitSystem) {
			case Metric:
				return convertInchesToMillimeter(value);
			case Imperial:
				return convertMillimeterToInches(value);
			default:
				return null;
		}
	}

	private Double convertInchesToMillimeter(Double value) {
		return value*25.4;
	}

	private Double convertMillimeterToInches(Double value) {
		return value/25.4;
	}
}
