package com.giora.climasale.features.addPlace.module;

import com.giora.climasale.features.addPlace.data.ILocationProvider;
import com.giora.climasale.features.addPlace.data.LocationProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class AddPlaceModule {

	@Provides
	ILocationProvider provideLocationProvider() {
		return new LocationProvider();
	}
}
