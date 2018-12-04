package com.giora.climasale.services.ioc.component;

import com.giora.climasale.features.capitalList.module.CapitalListModule;
import com.giora.climasale.features.capitalList.presentation.CapitalListActivity;
import com.giora.climasale.services.ioc.modules.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
		modules = {
				MainModule.class,
				CapitalListModule.class
		}
)
public interface MainComponent {
	void inject(CapitalListActivity capitalListActivity);
}
