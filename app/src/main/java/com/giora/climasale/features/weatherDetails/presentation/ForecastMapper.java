package com.giora.climasale.features.weatherDetails.presentation;

import com.giora.climasale.features.weatherDetails.domain.Forecast;
import com.giora.climasale.features.weatherDetails.domain.UnitSystem;

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

	private void mapMaxTemperature(ForecastViewModel forecastViewModel, Forecast forecast) {
		Double temperature = forecast.getMaxTemperature();
		if (temperature == null)
			return;

		forecastViewModel.setMaxTemperature(getTemperatureAsString(temperature, forecast.getUnitSystem()));
	}

	private void mapMinTemperature(ForecastViewModel forecastViewModel, Forecast forecast) {
		Double temperature = forecast.getMinTemperature();
		if (temperature == null)
			return;

		forecastViewModel.setMinTemperature(getTemperatureAsString(temperature, forecast.getUnitSystem()));
	}

	private void mapDate(ForecastViewModel forecastViewModel, Forecast forecast) {
		Date date = forecast.getDate();
		if (date == null)
			return;

		forecastViewModel.setDate(new SimpleDateFormat("dd.MM.yyyy").format(date));
	}

	private String getTemperatureAsString(Double temperature, UnitSystem unitSystem) {
		return String.format("%.2f %s", temperature, mapTemepratureUnitsToString(unitSystem));
	}

	private String mapTemepratureUnitsToString(UnitSystem unitSystem) {
		switch (unitSystem) {
			case Metric:
				return "°C";
			case Imperial:
				return "°F";
			default:
				return "°C";

		}
	}

	private void mapPrecipitation(ForecastViewModel forecastViewModel, Forecast forecast) {
		Double precipitation = forecast.getPrecipitation();
		if (precipitation == null)
			return;

		forecastViewModel.setPrecipitation(getPrecipitationAsString(precipitation, forecast.getUnitSystem()));
	}

	private String getPrecipitationAsString(Double precipitation, UnitSystem unitSystem) {
		return String.format("%.2f %s", precipitation, mapPrecipitationUnitsToString(unitSystem));
	}

	private String mapPrecipitationUnitsToString(UnitSystem unitSystem) {
		switch (unitSystem) {
			case Metric:
				return "mm/hr";
			case Imperial:
				return "in/hr";
			default:
				return "mm/hr";
		}
	}
}
