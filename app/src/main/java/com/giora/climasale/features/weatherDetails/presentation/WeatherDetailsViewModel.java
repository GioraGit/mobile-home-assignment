package com.giora.climasale.features.weatherDetails.presentation;

import android.arch.lifecycle.ViewModel;

import com.giora.climasale.R;
import com.giora.climasale.app.ClimaSaleApp;
import com.giora.climasale.features.weatherDetails.domain.IGetForecastsUseCase;

import java.util.ArrayList;
import java.util.List;


public class WeatherDetailsViewModel extends ViewModel {

	private final IGetForecastsUseCase getForecastsUseCase;
	private final IDayOfTheWeekImageProvider dayOfTheWeekImageProvider;

	public WeatherDetailsViewModel(IGetForecastsUseCase getForecastsUseCase,
								   IDayOfTheWeekImageProvider dayOfTheWeekImageProvider) {
		this.getForecastsUseCase = getForecastsUseCase;
		this.dayOfTheWeekImageProvider = dayOfTheWeekImageProvider;
	}

	List<ForecastViewModel> getInitialForecasts() {
		List<ForecastViewModel> forecastViewModels = new ArrayList<>();
		for (int dayIndex = 0; dayIndex < getForecastsUseCase.getNumberOfDays(); ++dayIndex) {
			ForecastViewModel forecastViewModel = new ForecastViewModel();
			forecastViewModel.initialText = ClimaSaleApp.getAppResources().getString(R.string.loading_forecast);
			forecastViewModel.dayOfTheWeekImage = dayOfTheWeekImageProvider.provideImage(dayIndex);
			forecastViewModels.add(forecastViewModel);
		}

		return forecastViewModels;
	}
}
