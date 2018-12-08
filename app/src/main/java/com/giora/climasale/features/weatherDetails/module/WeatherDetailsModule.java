package com.giora.climasale.features.weatherDetails.module;

import com.giora.climasale.features.weatherDetails.data.ForecastResultMapper;
import com.giora.climasale.features.weatherDetails.data.ForecastsFieldsParameterBuilder;
import com.giora.climasale.features.weatherDetails.data.ForecastsLiveApi;
import com.giora.climasale.features.weatherDetails.data.ForecastsRepository;
import com.giora.climasale.features.weatherDetails.data.IForecastRemoteApi;
import com.giora.climasale.features.weatherDetails.data.IForecastResultMapper;
import com.giora.climasale.features.weatherDetails.data.IForecastsFieldsParameterBuilder;
import com.giora.climasale.features.weatherDetails.data.IForecastsLiveApi;
import com.giora.climasale.features.weatherDetails.data.IPrecipitationConverter;
import com.giora.climasale.features.weatherDetails.data.ITemperatureConverter;
import com.giora.climasale.features.weatherDetails.data.PrecipitationConverter;
import com.giora.climasale.features.weatherDetails.data.TemperatureConverter;
import com.giora.climasale.features.weatherDetails.domain.GetForecastsUseCase;
import com.giora.climasale.features.weatherDetails.domain.IForecastsRepository;
import com.giora.climasale.features.weatherDetails.domain.IGetForecastsUseCase;
import com.giora.climasale.features.weatherDetails.presentation.DayOfTheWeekImageProvider;
import com.giora.climasale.features.weatherDetails.presentation.ForecastMapper;
import com.giora.climasale.features.weatherDetails.presentation.IDayOfTheWeekImageProvider;
import com.giora.climasale.features.weatherDetails.presentation.IForecastMapper;
import com.giora.climasale.features.weatherDetails.presentation.IWeatherDetailsViewModelFactory;
import com.giora.climasale.features.weatherDetails.presentation.WeatherDetailsViewModelFactory;
import com.giora.climasale.services.restClients.IClimaCellApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherDetailsModule {

	@Provides
	IDayOfTheWeekImageProvider provideDayOfTheWeekImageProvider() {
		return new DayOfTheWeekImageProvider();
	}

	@Singleton
	@Provides
	IForecastRemoteApi provideForecastRemoteApi(IClimaCellApiClient climaCellApiClient) {
		return climaCellApiClient.createApi(IForecastRemoteApi.class);
	}

	@Provides
	IForecastsLiveApi provideForecastsLiveApi(IForecastRemoteApi forecastRemoteApi) {
		return new ForecastsLiveApi(forecastRemoteApi);
	}

	@Provides
	IForecastsFieldsParameterBuilder provideForecastsFieldsParameterBuilder() {
		return new ForecastsFieldsParameterBuilder();
	}

	@Provides
	IForecastResultMapper provideForecastResultMapper() {
		return new ForecastResultMapper();
	}

	@Provides
	@Singleton
	ITemperatureConverter provideTemperatureConverter() {
		return new TemperatureConverter();
	}

	@Provides
	@Singleton
	IPrecipitationConverter providePrecipitationConverter() {
		return new PrecipitationConverter();
	}

	@Provides
	IForecastsRepository provideForecastsRepository(IForecastsLiveApi forecastsLiveApi,
													IForecastsFieldsParameterBuilder forecastsFieldsParameterBuilder,
													IForecastResultMapper forecastResultMapper,
													ITemperatureConverter temperatureConverter,
													IPrecipitationConverter precipitationConverter) {
		return new ForecastsRepository(forecastsLiveApi, forecastsFieldsParameterBuilder,
				forecastResultMapper, temperatureConverter, precipitationConverter);
	}

	@Provides
	IGetForecastsUseCase provideGetForecastsUseCase(IForecastsRepository forecastsRepository) {
		return new GetForecastsUseCase(forecastsRepository);
	}

	@Provides
	IForecastMapper provideForecastMapper() {
		return new ForecastMapper();
	}

	@Provides
	IWeatherDetailsViewModelFactory provideWeatherDetailsViewModelFactory(IGetForecastsUseCase getForecastsUseCase,
																		  IDayOfTheWeekImageProvider dayOfTheWeekImageProvider,
																		  IForecastMapper forecastMapper) {
		return new WeatherDetailsViewModelFactory(getForecastsUseCase, dayOfTheWeekImageProvider, forecastMapper);
	}
}
