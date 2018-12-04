package com.giora.climasale.services.restClients;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient implements ICountriesRestClient {

	private Retrofit retrofit;

	public RestClient(String baseUrl) {
		retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	@Override
	public <T> T createApi(@NonNull Class<T> service) {
		return retrofit.create(service);
	}
}
