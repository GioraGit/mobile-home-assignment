package com.giora.climasale.features.capitalList.data;

import com.google.gson.annotations.SerializedName;

class CapitalResult {

	@SerializedName("capital")
	String capital;

	@SerializedName("name")
	String country;

	@SerializedName("flag")
	String flag;

	@SerializedName("latlng")
	double[] latLng;
}
