package com.giora.climasale.features.weatherDetails.presentation;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.giora.climasale.R;
import com.giora.climasale.app.ClimaSaleApp;
import com.giora.climasale.features.weatherDetails.domain.Forecast;
import com.giora.climasale.features.weatherDetails.domain.IGetForecastsUseCase;
import com.giora.climasale.features.weatherDetails.domain.UnitSystem;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;


class WeatherDetailsViewModel extends ViewModel {

	private final IGetForecastsUseCase getForecastsUseCase;
	private final IDayOfTheWeekImageProvider dayOfTheWeekImageProvider;
	private final IForecastMapper forecastMapper;

	WeatherDetailsViewModel(IGetForecastsUseCase getForecastsUseCase,
								   IDayOfTheWeekImageProvider dayOfTheWeekImageProvider,
								   IForecastMapper forecastMapper) {
		this.getForecastsUseCase = getForecastsUseCase;
		this.dayOfTheWeekImageProvider = dayOfTheWeekImageProvider;
		this.forecastMapper = forecastMapper;
	}

	@NonNull
	List<ForecastViewModel> getInitialForecasts() {
		List<ForecastViewModel> forecastViewModels = new ArrayList<>();
		for (int dayIndex = 0; dayIndex < getForecastsUseCase.getNumberOfDays(); ++dayIndex) {
			ForecastViewModel forecastViewModel = new ForecastViewModel();
			forecastViewModel.setInitialText(ClimaSaleApp.getAppResources().getString(R.string.loading_forecast));
			forecastViewModel.dayOfTheWeekImage = dayOfTheWeekImageProvider.provideImage(dayIndex);
			forecastViewModels.add(forecastViewModel);
		}

		return forecastViewModels;
	}

	@NonNull
	LiveData<List<ForecastViewModel>> getForecasts(LatLng latLng) {
		LiveData<List<Forecast>> forecasts = getForecastsUseCase.getForecasts(latLng, getInitialUnitSystem());
		return Transformations.map(forecasts, new Function<List<Forecast>, List<ForecastViewModel>>() {
			@Override
			public List<ForecastViewModel> apply(List<Forecast> input) {
				List<ForecastViewModel> forecastViewModels = new ArrayList<>();
				for (int forecastIndex = 0; forecastIndex < input.size(); ++forecastIndex) {
					ForecastViewModel forecastViewModel = forecastMapper.map(input.get(forecastIndex));
					forecastViewModel.dayOfTheWeekImage = dayOfTheWeekImageProvider.provideImage(forecastIndex);
					forecastViewModels.add(forecastViewModel);
				}

				return forecastViewModels;
			}
		});
	}

	private UnitSystem getInitialUnitSystem() {
		return UnitSystem.Metric;
	}

	void toggleUnitSystem() {
		getForecastsUseCase.toggleUnitSystem();
	}
}
