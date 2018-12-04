package com.giora.climasale.services.ioc.modules;

import com.giora.climasale.R;
import com.giora.climasale.app.ClimaSaleApp;
import com.giora.climasale.services.restClients.ICountriesRestClient;
import com.giora.climasale.services.restClients.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

	private final ClimaSaleApp application;

	public MainModule(ClimaSaleApp application) {
		this.application = application;
	}

	@Provides
	@Singleton
	public ICountriesRestClient provideRestClient() {
		return new RestClient(application.getResources().getString(R.string.countries_host_name));
	}
}
