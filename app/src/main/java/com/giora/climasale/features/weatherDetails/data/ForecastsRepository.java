package com.giora.climasale.features.weatherDetails.data;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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
	private final ITemperatureConverter temperatureConverter;
	private final IPrecipitationConverter precipitationConverter;
	private final MutableLiveData<List<Forecast>> forecastsLiveData = initForecastsLiveData();

	public ForecastsRepository(IForecastsLiveApi forecastsLiveApi,
							   IForecastsFieldsParameterBuilder forecastsFieldsParameterBuilder,
							   IForecastResultMapper forecastResultMapper,
							   ITemperatureConverter temperatureConverter,
							   IPrecipitationConverter precipitationConverter) {
		this.forecastsLiveApi = forecastsLiveApi;
		this.forecastsFieldsParameterBuilder = forecastsFieldsParameterBuilder;
		this.forecastResultMapper = forecastResultMapper;
		this.temperatureConverter = temperatureConverter;
		this.precipitationConverter = precipitationConverter;
	}

	@NonNull
	@Override
	public LiveData<List<Forecast>> getForecasts(LatLng latLng, int numberOfDays, final UnitSystem unitSystem,
												 ForecastProperty[] forecastProperties) {
		if (forecastsLiveData.getValue() != null && !forecastsLiveData.getValue().isEmpty())
			return forecastsLiveData;

		LiveData<List<ForecastResult>> forecastResults = forecastsLiveApi.getForecasts(latLng.latitude,
				latLng.longitude, numberOfDays, getUnitSystem(unitSystem), forecastsFieldsParameterBuilder.build(forecastProperties));

		LiveData<List<Forecast>> forecasts = Transformations.map(forecastResults, new Function<List<ForecastResult>, List<Forecast>>() {
			@Override
			public List<Forecast> apply(List<ForecastResult> input) {
				List<Forecast> forecasts = new ArrayList<>();
				for (ForecastResult forecastResult : input)
					forecasts.add(forecastResultMapper.map(forecastResult, unitSystem));

				forecastsLiveData.setValue(forecasts);
				return forecasts;
			}
		});

		return Transformations.switchMap(forecasts, new Function<List<Forecast>, LiveData<List<Forecast>>>() {
			@Override
			public LiveData<List<Forecast>> apply(List<Forecast> input) {
				return forecastsLiveData;
			}
		});
	}

	@Override
	public void toggleUnitSystem() {
		List<Forecast> currentForecasts = forecastsLiveData.getValue();
		if (currentForecasts == null)
			return;

		List<Forecast> newForecasts = new ArrayList<>(currentForecasts.size());
		for (Forecast forecast : currentForecasts) {
			UnitSystem newUnitSystem = toggleUnitSystem(forecast.getUnitSystem());
			newForecasts.add(new Forecast(forecast.getDate(),
					newUnitSystem, temperatureConverter.convertTemperature(forecast.getMinTemperature(), newUnitSystem),
					temperatureConverter.convertTemperature(forecast.getMaxTemperature(), newUnitSystem),
					precipitationConverter.convertPrecipitation(forecast.getPrecipitation(), newUnitSystem)));
		}

		forecastsLiveData.setValue(newForecasts);
	}

	private UnitSystem toggleUnitSystem(UnitSystem unitSystem) {
		switch (unitSystem) {
			case Metric:
				return UnitSystem.Imperial;
			case Imperial:
				return UnitSystem.Metric;
			default:
				return UnitSystem.Metric;
		}
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

	private MutableLiveData<List<Forecast>> initForecastsLiveData() {
		List<Forecast> forecasts = new ArrayList<>();
		MutableLiveData<List<Forecast>> mutableLiveData = new MutableLiveData<>();
		mutableLiveData.setValue(forecasts);
		return mutableLiveData;
	}
}
