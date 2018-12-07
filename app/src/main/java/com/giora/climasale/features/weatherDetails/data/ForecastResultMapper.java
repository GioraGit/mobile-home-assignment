package com.giora.climasale.features.weatherDetails.data;

import com.giora.climasale.features.weatherDetails.domain.Forecast;
import com.giora.climasale.features.weatherDetails.domain.Precipitation;
import com.giora.climasale.features.weatherDetails.domain.PrecipitationUnits;
import com.giora.climasale.features.weatherDetails.domain.Temperature;
import com.giora.climasale.features.weatherDetails.domain.TemperatureUnits;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ForecastResultMapper implements IForecastResultMapper {
	@Override
	public Forecast map(ForecastResult forecastResult) {
		if (forecastResult == null)
			return null;

		return new Forecast(getDate(forecastResult.observationTime), getMinTemperature(forecastResult.temperatures),
				getMaxTemperature(forecastResult.temperatures), getPrecipitation(forecastResult.precipitationArray));
	}

	private Temperature getMaxTemperature(TemperatureResult[] temperatures) {
		if (temperatures == null || temperatures.length< 2)
			return null;

		ValueInstanceResult maxTemperatureResult = temperatures[1].max;
		if (maxTemperatureResult == null)
			return null;

		return new Temperature(maxTemperatureResult.value, mapTemperatureUnits(maxTemperatureResult.units));
	}

	private Temperature getMinTemperature(TemperatureResult[] temperatures) {
		if (temperatures == null || temperatures.length< 2)
			return null;

		ValueInstanceResult minTemperatureResult = temperatures[0].min;
		if (minTemperatureResult == null)
			return null;

		return new Temperature(minTemperatureResult.value, mapTemperatureUnits(minTemperatureResult.units));
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

	private TemperatureUnits mapTemperatureUnits(String temperatureUnits) {
		if (temperatureUnits.equals("C"))
			return TemperatureUnits.Celsius;

		return TemperatureUnits.Fahrenheit;
	}

	private Precipitation getPrecipitation(PrecipitationResult[] precipitationArray) {
		if (precipitationArray == null || precipitationArray.length == 0)
			return null;

		PrecipitationResult precipitationResult = precipitationArray[0];
		if (precipitationResult == null)
			return null;

		ValueInstanceResult precipitationInstanceResult = precipitationResult.max;
		if (precipitationInstanceResult == null)
			return null;

		return new Precipitation(precipitationInstanceResult.value, mapPrecipitationUnits(precipitationInstanceResult.units));
	}

	private PrecipitationUnits mapPrecipitationUnits(String precipitationUnits) {
		if (precipitationUnits.equals("mm/hr"))
			return PrecipitationUnits.MillimetersPerHour;

		return PrecipitationUnits.InchesPerHour;
	}
}
