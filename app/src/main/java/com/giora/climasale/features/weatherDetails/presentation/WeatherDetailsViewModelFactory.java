package com.giora.climasale.features.weatherDetails.presentation;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.giora.climasale.features.weatherDetails.domain.IGetForecastsUseCase;

public class WeatherDetailsViewModelFactory implements IWeatherDetailsViewModelFactory {

	private final IGetForecastsUseCase getForecastsUseCase;
	private final IDayOfTheWeekImageProvider dayOfTheWeekImageProvider;
	private final IForecastMapper forecastMapper;

	public WeatherDetailsViewModelFactory(IGetForecastsUseCase getForecastsUseCase,
										  IDayOfTheWeekImageProvider dayOfTheWeekImageProvider,
										  IForecastMapper forecastMapper) {
		this.getForecastsUseCase = getForecastsUseCase;
		this.dayOfTheWeekImageProvider = dayOfTheWeekImageProvider;
		this.forecastMapper = forecastMapper;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		if (modelClass == WeatherDetailsViewModel.class)
			return modelClass.cast(new WeatherDetailsViewModel(getForecastsUseCase, dayOfTheWeekImageProvider, forecastMapper));
		else
			throw new IllegalArgumentException("ViewModel not found");
	}
}
