package com.giora.climasale.features.weatherDetails.data;

import com.google.gson.annotations.SerializedName;

class TemperatureInstanceResult {

	@SerializedName("value")
	double value;

	@SerializedName("units")
	String units;
}
