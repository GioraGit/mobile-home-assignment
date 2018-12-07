package com.giora.climasale.features.weatherDetails.presentation;

import com.giora.climasale.features.weatherDetails.domain.Forecast;
import com.giora.climasale.features.weatherDetails.domain.Precipitation;
import com.giora.climasale.features.weatherDetails.domain.PrecipitationUnits;
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
		mapPrecipitation(forecastViewModel, forecast);
		return forecastViewModel;
	}

	private void mapPrecipitation(ForecastViewModel forecastViewModel, Forecast forecast) {
		Precipitation precipitation = forecast.getPrecipitation();
		if (precipitation == null)
			return;

		forecastViewModel.setPrecipitation(getPrecipitationAsString(precipitation));
	}

	private String getPrecipitationAsString(Precipitation precipitation) {
		return String.format("%.2f %s", precipitation.getPrecipitation(), mapPrecipitationUnitsToString(precipitation.getPrecipitationUnits()));
	}

	private String mapPrecipitationUnitsToString(PrecipitationUnits precipitationUnits) {
		switch (precipitationUnits) {
			case MillimetersPerHour:
				return "mm/hr";
			case InchesPerHour:
				return "in/hr";
			default:
				return "mm/hr";
		}
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
