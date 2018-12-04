package com.giora.climasale.app;

import android.app.Application;
import android.content.res.Resources;

import com.giora.climasale.services.ioc.component.ComponentManager;

public class ClimaSaleApp extends Application {

	private static Resources resources;

	public static Resources getAppResources() {
		return resources;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		initializeIOC();
		resources = getResources();
	}

	private void initializeIOC() {
		ComponentManager.buildComponent(this);
	}
}
