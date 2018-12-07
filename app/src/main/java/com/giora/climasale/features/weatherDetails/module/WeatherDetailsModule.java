package com.giora.climasale.features.weatherDetails.module;

import com.giora.climasale.features.weatherDetails.domain.GetForecastsUseCase;
import com.giora.climasale.features.weatherDetails.domain.IGetForecastsUseCase;
import com.giora.climasale.features.weatherDetails.presentation.DayOfTheWeekImageProvider;
import com.giora.climasale.features.weatherDetails.presentation.IDayOfTheWeekImageProvider;
import com.giora.climasale.features.weatherDetails.presentation.IWeatherDetailsViewModelFactory;
import com.giora.climasale.features.weatherDetails.presentation.WeatherDetailsViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherDetailsModule {

	@Provides
	IDayOfTheWeekImageProvider provideDayOfTheWeekImageProvider() {
		return new DayOfTheWeekImageProvider();
	}

	@Provides
	IGetForecastsUseCase provideGetForecastsUseCase() {
		return new GetForecastsUseCase(forecastsRepository);
	}

	@Singleton
	@Provides
	IWeatherDetailsViewModelFactory provideWeatherDetailsViewModelFactory(IGetForecastsUseCase getForecastsUseCase,
																		  IDayOfTheWeekImageProvider dayOfTheWeekImageProvider) {
		return new WeatherDetailsViewModelFactory(getForecastsUseCase, dayOfTheWeekImageProvider);
	}
}
