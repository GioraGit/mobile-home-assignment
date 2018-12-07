package com.giora.climasale.features.weatherDetails.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastsLiveApi implements IForecastsLiveApi {

	private final IForecastRemoteApi forecastRemoteApi;

	public ForecastsLiveApi(IForecastRemoteApi forecastRemoteApi) {
		this.forecastRemoteApi = forecastRemoteApi;
	}

	@NonNull
	@Override
	public LiveData<List<ForecastResult>> getForecasts(double latitude, double longitude, int numberOfDays,
													   String unitSystem, String fields) {
		final MutableLiveData<List<ForecastResult>> forecastResults = new MutableLiveData<>();
		forecastRemoteApi.getForecasts(latitude, longitude, numberOfDays, unitSystem, fields)
				.enqueue(new Callback<List<ForecastResult>>() {
					@Override
					public void onResponse(Call<List<ForecastResult>> call, Response<List<ForecastResult>> response) {
						if (!response.isSuccessful() || response.body() == null)
							return;

						forecastResults.setValue(response.body());
					}

					@Override
					public void onFailure(Call<List<ForecastResult>> call, Throwable t) {

					}
				});

		return forecastResults;
	}
}
