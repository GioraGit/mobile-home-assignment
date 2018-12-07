package com.giora.climasale.features.weatherDetails.domain;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface IForecastsRepository {

	@NonNull
	LiveData<List<Forecast>> getForecasts(LatLng latLng, int numberOfDays, UnitSystem unitSystem,
										  ForecastProperty[] forecastProperties);
}
