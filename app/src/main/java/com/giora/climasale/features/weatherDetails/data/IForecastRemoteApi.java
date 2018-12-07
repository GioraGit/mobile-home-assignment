package com.giora.climasale.features.weatherDetails.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IForecastRemoteApi {

	@GET("weather/forecast/daily")
	Call<List<ForecastResult>> getForecasts(@Query("lat") double latitude,
											@Query("lon") double longitude,
											@Query("num_days") int numberOfDays,
											@Query("unit_system") String unitSystem,
											@Query("fields") String fields);
}
