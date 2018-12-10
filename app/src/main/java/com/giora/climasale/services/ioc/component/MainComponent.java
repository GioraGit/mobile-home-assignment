package com.giora.climasale.services.ioc.component;

import com.giora.climasale.features.addPlace.module.AddPlaceModule;
import com.giora.climasale.features.addPlace.presentation.AddPlaceActivity;
import com.giora.climasale.features.capitalList.module.CapitalListModule;
import com.giora.climasale.features.capitalList.presentation.CapitalListActivity;
import com.giora.climasale.features.seeOnMap.presentation.SeeOnMapActivity;
import com.giora.climasale.features.weatherDetails.module.WeatherDetailsModule;
import com.giora.climasale.features.weatherDetails.presentation.WeatherDetailsActivity;
import com.giora.climasale.services.ioc.modules.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
		modules = {
				MainModule.class,
				CapitalListModule.class,
				WeatherDetailsModule.class,
				AddPlaceModule.class
		}
)
public interface MainComponent {
	void inject(CapitalListActivity capitalListActivity);

	void inject(WeatherDetailsActivity weatherDetailsActivity);

	void inject(SeeOnMapActivity seeOnMapActivity);

	void inject(AddPlaceActivity addPlaceActivity);
}
