package com.giora.climasale.features.weatherDetails.data;

import com.google.gson.annotations.SerializedName;

class TemperatureResult {

	@SerializedName("min")
	TemperatureInstanceResult min;

	@SerializedName("max")
	TemperatureInstanceResult max;
}
