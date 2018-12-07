package com.giora.climasale.features.weatherDetails.data;

import com.google.gson.annotations.SerializedName;

class TemperatureResult {

	@SerializedName("min")
	ValueInstanceResult min;

	@SerializedName("max")
	ValueInstanceResult max;
}
