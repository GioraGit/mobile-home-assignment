package com.giora.climasale.features.capitalList.module;

import com.giora.climasale.features.capitalList.data.CapitalFieldsParameterBuilder;
import com.giora.climasale.features.capitalList.data.CapitalsLiveApi;
import com.giora.climasale.features.capitalList.data.CapitalsRepository;
import com.giora.climasale.features.capitalList.data.ICapitalFieldsParameterBuilder;
import com.giora.climasale.features.capitalList.data.ICapitalsLiveApi;
import com.giora.climasale.features.capitalList.data.ICapitalsRemoteApi;
import com.giora.climasale.features.capitalList.domain.GetCapitalsUseCase;
import com.giora.climasale.features.capitalList.domain.ICapitalsRepository;
import com.giora.climasale.features.capitalList.domain.IGetCapitalsUseCase;
import com.giora.climasale.features.capitalList.presentation.CapitalListViewModelFactory;
import com.giora.climasale.features.capitalList.presentation.CapitalMapper;
import com.giora.climasale.features.capitalList.presentation.ICapitalListViewModelFactory;
import com.giora.climasale.features.capitalList.presentation.ICapitalMapper;
import com.giora.climasale.services.restClients.ICountriesRestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CapitalListModule {

	@Singleton
	@Provides
	ICapitalsRemoteApi provideCapitalsRemoteApi(ICountriesRestClient countriesRestClient) {
		return countriesRestClient.createApi(ICapitalsRemoteApi.class);
	}

	@Provides
	ICapitalFieldsParameterBuilder provideCapitalFieldsParameterBuilder() {
		return new CapitalFieldsParameterBuilder();
	}

	@Provides
	ICapitalsLiveApi provideCapitalsLiveApi(ICapitalsRemoteApi capitalsRemoteApi) {
		return new CapitalsLiveApi(capitalsRemoteApi);
	}

	@Provides
	ICapitalsRepository provideCapitalsRepository(ICapitalsLiveApi capitalsLiveApi,
												  ICapitalFieldsParameterBuilder capitalFieldsParameterBuilder) {
		return new CapitalsRepository(capitalsLiveApi, capitalFieldsParameterBuilder);
	}

	@Provides
	IGetCapitalsUseCase provideGetCapitalsUseCase(ICapitalsRepository capitalsRepository) {
		return new GetCapitalsUseCase(capitalsRepository);
	}

	@Provides
	ICapitalMapper provideCapitalMapper() {
		return new CapitalMapper();
	}

	@Singleton
	@Provides
	ICapitalListViewModelFactory provideCapitalListViewModelFactory(IGetCapitalsUseCase getCapitalsUseCase,
																	ICapitalMapper capitalMapper) {
		return new CapitalListViewModelFactory(getCapitalsUseCase, capitalMapper);
	}
}
