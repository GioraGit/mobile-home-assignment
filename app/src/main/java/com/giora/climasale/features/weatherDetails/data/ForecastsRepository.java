package com.giora.climasale.features.weatherDetails.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.giora.climasale.features.weatherDetails.domain.Forecast;
import com.giora.climasale.features.weatherDetails.domain.ForecastProperty;
import com.giora.climasale.features.weatherDetails.domain.IForecastsRepository;
import com.giora.climasale.features.weatherDetails.domain.UnitSystem;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

class ForecastsRepository implements IForecastsRepository {

	private final IForecastsLiveApi forecastsLiveApi;
	private final IForecastsFieldsParameterBuilder forecastsFieldsParameterBuilder;

	ForecastsRepository(IForecastsLiveApi forecastsLiveApi,
						IForecastsFieldsParameterBuilder forecastsFieldsParameterBuilder) {
		this.forecastsLiveApi = forecastsLiveApi;
		this.forecastsFieldsParameterBuilder = forecastsFieldsParameterBuilder;
	}

	@NonNull
	@Override
	public LiveData<List<Forecast>> getForecasts(LatLng latLng, int numberOfDays, UnitSystem unitSystem,
												 ForecastProperty[] forecastProperties) {
		LiveData<List<ForecastResult>> forecastResults = forecastsLiveApi.getForecasts(latLng.latitude,
				latLng.longitude, numberOfDays, getUnitSystem(unitSystem), forecastsFieldsParameterBuilder.build(forecastProperties));

		return null;
	}
}
