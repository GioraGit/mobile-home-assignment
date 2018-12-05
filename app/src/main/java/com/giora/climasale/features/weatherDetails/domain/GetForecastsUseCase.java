package com.giora.climasale.features.weatherDetails.domain;

public class GetForecastsUseCase implements IGetForecastsUseCase {
	@Override
	public int getNumberOfDays() {
		return 5;
	}
}
