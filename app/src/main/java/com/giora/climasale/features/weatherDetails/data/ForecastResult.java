package com.giora.climasale.features.weatherDetails.data;

import com.google.gson.annotations.SerializedName;

class ForecastResult {

	@SerializedName("observation_time")
	ObservationTimeResult observationTime;

	@SerializedName("temp")
	TemperatureResult[] temperatures;

	@SerializedName("precipitation")
	PrecipitationResult[] precipitationArray;
}
