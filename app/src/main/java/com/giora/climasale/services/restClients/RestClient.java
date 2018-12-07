package com.giora.climasale.services.restClients;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient implements ICountriesRestClient, IClimaCellApiClient {

	private Retrofit retrofit;

	public RestClient(String baseUrl, OkHttpClient client) {
		retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build();
	}

	@Override
	public <T> T createApi(@NonNull Class<T> service) {
		return retrofit.create(service);
	}
}
