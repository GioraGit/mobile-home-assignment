package com.giora.climasale.features.weatherDetails.domain;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class GetForecastsUseCase implements IGetForecastsUseCase {

	private final IForecastsRepository forecastsRepository;

	public GetForecastsUseCase(IForecastsRepository forecastsRepository) {
		this.forecastsRepository = forecastsRepository;
	}

	@Override
	public int getNumberOfDays() {
		return 5;
	}

	@NonNull
	@Override
	public LiveData<List<Forecast>> getForecasts(LatLng latLng, UnitSystem unitSystem) {
		return forecastsRepository.getForecasts(latLng, getNumberOfDays(), unitSystem,
				new ForecastProperty[]{ForecastProperty.Temperature, ForecastProperty.Precipitation});
	}

	@Override
	public void toggleUnitSystem() {
		forecastsRepository.toggleUnitSystem();
	}
}
