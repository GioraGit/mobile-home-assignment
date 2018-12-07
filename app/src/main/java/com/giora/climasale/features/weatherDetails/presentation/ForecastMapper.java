package com.giora.climasale.features.weatherDetails.presentation;

import com.giora.climasale.features.weatherDetails.domain.Forecast;
import com.giora.climasale.features.weatherDetails.domain.Temperature;
import com.giora.climasale.features.weatherDetails.domain.TemperatureUnits;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ForecastMapper implements IForecastMapper {
	@Override
	public ForecastViewModel map(Forecast forecast) {
		if (forecast == null)
			return null;

		ForecastViewModel forecastViewModel = new ForecastViewModel();
		mapDate(forecastViewModel, forecast);
		mapMinTemperature(forecastViewModel, forecast);
		mapMaxTemperature(forecastViewModel, forecast);
		return forecastViewModel;
	}

	private void mapMaxTemperature(ForecastViewModel forecastViewModel, Forecast forecast) {
		Temperature temperature = forecast.getMaxTemperature();
		if (temperature == null)
			return;

		forecastViewModel.setMaxTemperature(getTemperatureAsString(temperature));
	}

	private void mapMinTemperature(ForecastViewModel forecastViewModel, Forecast forecast) {
		Temperature temperature = forecast.getMinTemperature();
		if (temperature == null)
			return;

		forecastViewModel.setMinTemperature(getTemperatureAsString(temperature));
	}

	private void mapDate(ForecastViewModel forecastViewModel, Forecast forecast) {
		Date date = forecast.getDate();
		if (date == null)
			return;

		forecastViewModel.setDate(new SimpleDateFormat("dd.MM.yyyy").format(date));
	}

	private String getTemperatureAsString(Temperature temperature) {
		return String.format("%.2f %s", temperature.getTemperature(), mapTemepratureUnitsToString(temperature.getTemperatureUnits()));
	}

	private String mapTemepratureUnitsToString(TemperatureUnits temperatureUnits) {
		switch (temperatureUnits) {
			case Celsius:
				return "°C";
			case Fahrenheit:
				return "°F";
			default:
				return "°C";

		}
	}
}
