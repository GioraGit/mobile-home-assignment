package com.giora.climasale.features.weatherDetails.data;

import com.giora.climasale.features.weatherDetails.domain.Forecast;
import com.giora.climasale.features.weatherDetails.domain.UnitSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ForecastResultMapper implements IForecastResultMapper {
	@Override
	public Forecast map(ForecastResult forecastResult, UnitSystem unitSystem) {
		if (forecastResult == null)
			return null;

		return new Forecast(getDate(forecastResult.observationTime), unitSystem, getMinTemperature(forecastResult.temperatures),
				getMaxTemperature(forecastResult.temperatures), getPrecipitation(forecastResult.precipitationArray));
	}

	private Double getMaxTemperature(TemperatureResult[] temperatures) {
		if (temperatures == null || temperatures.length< 2)
			return null;

		ValueInstanceResult maxTemperatureResult = temperatures[1].max;
		if (maxTemperatureResult == null)
			return null;

		return maxTemperatureResult.value;
	}

	private Double getMinTemperature(TemperatureResult[] temperatures) {
		if (temperatures == null || temperatures.length< 2)
			return null;

		ValueInstanceResult minTemperatureResult = temperatures[0].min;
		if (minTemperatureResult == null)
			return null;

		return minTemperatureResult.value;
	}

	private Date getDate(ObservationTimeResult observationTimeResult) {
		if (observationTimeResult == null)
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(observationTimeResult.value);
		} catch (ParseException e) {
			return null;
		}
	}

	private Double getPrecipitation(PrecipitationResult[] precipitationArray) {
		if (precipitationArray == null || precipitationArray.length == 0)
			return null;

		PrecipitationResult precipitationResult = precipitationArray[0];
		if (precipitationResult == null)
			return null;

		ValueInstanceResult precipitationInstanceResult = precipitationResult.max;
		if (precipitationInstanceResult == null)
			return null;

		return precipitationInstanceResult.value;
	}
}
