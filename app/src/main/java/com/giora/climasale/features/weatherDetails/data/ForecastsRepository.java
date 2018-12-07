package com.giora.climasale.features.weatherDetails.data;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.giora.climasale.features.weatherDetails.domain.Forecast;
import com.giora.climasale.features.weatherDetails.domain.ForecastProperty;
import com.giora.climasale.features.weatherDetails.domain.IForecastsRepository;
import com.giora.climasale.features.weatherDetails.domain.UnitSystem;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class ForecastsRepository implements IForecastsRepository {

	private final IForecastsLiveApi forecastsLiveApi;
	private final IForecastsFieldsParameterBuilder forecastsFieldsParameterBuilder;
	private final IForecastResultMapper forecastResultMapper;

	public ForecastsRepository(IForecastsLiveApi forecastsLiveApi,
						IForecastsFieldsParameterBuilder forecastsFieldsParameterBuilder,
						IForecastResultMapper forecastResultMapper) {
		this.forecastsLiveApi = forecastsLiveApi;
		this.forecastsFieldsParameterBuilder = forecastsFieldsParameterBuilder;
		this.forecastResultMapper = forecastResultMapper;
	}

	@NonNull
	@Override
	public LiveData<List<Forecast>> getForecasts(LatLng latLng, int numberOfDays, UnitSystem unitSystem,
												 ForecastProperty[] forecastProperties) {
		LiveData<List<ForecastResult>> forecastResults = forecastsLiveApi.getForecasts(latLng.latitude,
				latLng.longitude, numberOfDays, getUnitSystem(unitSystem), forecastsFieldsParameterBuilder.build(forecastProperties));

		return Transformations.map(forecastResults, new Function<List<ForecastResult>, List<Forecast>>() {
			@Override
			public List<Forecast> apply(List<ForecastResult> input) {
				List<Forecast> forecasts = new ArrayList<>();
				for (ForecastResult forecastResult : input)
					forecasts.add(forecastResultMapper.map(forecastResult));

				return forecasts;
			}
		});
	}

	private String getUnitSystem(UnitSystem unitSystem) {
		switch (unitSystem) {
			case Metric:
				return "si";
			case Imperial:
				return "us";
			default:
				return "si";
		}
	}
}
