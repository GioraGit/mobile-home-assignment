package com.giora.climasale.features.weatherDetails.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public interface IForecastsLiveApi {

	@NonNull
	LiveData<List<ForecastResult>> getForecasts(double latitude, double longitude, int numberOfDays,
												String unitSystem, String fields);
}
