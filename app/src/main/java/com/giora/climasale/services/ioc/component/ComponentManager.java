package com.giora.climasale.services.ioc.component;

import com.giora.climasale.app.ClimaSaleApp;
import com.giora.climasale.services.ioc.modules.MainModule;

import javax.inject.Singleton;

@Singleton
public class ComponentManager {

	private static MainComponent component;

	public static void buildComponent(ClimaSaleApp application) {
		component = DaggerMainComponent.builder()
				.mainModule(new MainModule(application))
				.build();
	}

	public static MainComponent getComponent() {
		return component;
	}
}
