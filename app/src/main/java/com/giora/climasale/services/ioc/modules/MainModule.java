package com.giora.climasale.services.ioc.modules;

import com.giora.climasale.R;
import com.giora.climasale.app.ClimaSaleApp;
import com.giora.climasale.services.location.ILatLngProvider;
import com.giora.climasale.services.location.LatLngProvider;
import com.giora.climasale.services.restClients.IClimaCellApiClient;
import com.giora.climasale.services.restClients.ICountriesRestClient;
import com.giora.climasale.services.restClients.OkHttpClientWithCustomHeaderBuilder;
import com.giora.climasale.services.restClients.RestClient;

import java.util.HashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class MainModule {

	private final ClimaSaleApp application;

	public MainModule(ClimaSaleApp application) {
		this.application = application;
	}

	@Provides
	@Singleton
	IClimaCellApiClient provideClimaCellApiClient() {
		HashMap<String, String> customHeaderParameters = new HashMap<>();
		customHeaderParameters.put(application.getResources().getString(R.string.climacell_api_key_name),
				application.getResources().getString(R.string.climacell_api_key_value));

		return new RestClient(application.getResources().getString(R.string.climacell_host_name),
				OkHttpClientWithCustomHeaderBuilder.build(customHeaderParameters));
	}

	@Provides
	@Singleton
	ICountriesRestClient provideRestClient() {
		return new RestClient(application.getResources().getString(R.string.countries_host_name),
				new OkHttpClient());
	}

	@Provides
	ILatLngProvider provideLatLngProvider() {
		return new LatLngProvider();
	}
}
