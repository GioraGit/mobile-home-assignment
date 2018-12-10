package com.giora.climasale.features.capitalList.data;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.giora.climasale.features.addPlace.data.ILocationProvider;
import com.giora.climasale.features.capitalList.domain.Capital;
import com.giora.climasale.features.capitalList.domain.CapitalProperty;
import com.giora.climasale.features.capitalList.domain.ICapitalsRepository;

import java.util.ArrayList;
import java.util.List;


public class CapitalsRepository implements ICapitalsRepository {

	private final ICapitalsLiveApi capitalsLiveApi;
	private final ICapitalFieldsParameterBuilder capitalFieldsParameterBuilder;
	private final ILocationProvider locationProvider;
	private MutableLiveData<List<Capital>> capitalsLiveData = initCapitalsLiveData();

	public CapitalsRepository(ICapitalsLiveApi capitalsLiveApi,
							  ICapitalFieldsParameterBuilder capitalFieldsParameterBuilder,
							  ILocationProvider locationProvider) {
		this.capitalsLiveApi = capitalsLiveApi;
		this.capitalFieldsParameterBuilder = capitalFieldsParameterBuilder;
		this.locationProvider = locationProvider;
	}

	@NonNull
	@Override
	public LiveData<List<Capital>> getCapitals(CapitalProperty[] capitalProperties) {
		if (capitalsLiveData.getValue() != null && !capitalsLiveData.getValue().isEmpty())
			return capitalsLiveData;

		LiveData<List<CapitalResult>> capitalResults = capitalsLiveApi.getCapitals(capitalFieldsParameterBuilder.build(capitalProperties));

		LiveData<List<Capital>> capitals = Transformations.map(capitalResults, new Function<List<CapitalResult>, List<Capital>>() {
			@Override
			public List<Capital> apply(List<CapitalResult> input) {
				List<Capital> capitals = new ArrayList<>();
				for (CapitalResult capitalResult : input) {
					double[] latLng = getLatLng(capitalResult);
					capitals.add(new Capital(capitalResult.capital, capitalResult.country,
							capitalResult.flag, latLng[0], latLng[1]));
				}

				capitalsLiveData.setValue(capitals);
				return capitals;
			}
		});

		return Transformations.switchMap(capitals, new Function<List<Capital>, LiveData<List<Capital>>>() {
			@Override
			public LiveData<List<Capital>> apply(List<Capital> input) {
				return capitalsLiveData;
			}
		});
	}

	@Override
	public void addLocation(String location, String country) {
		List<Capital> currentCapitals = capitalsLiveData.getValue();
		if (currentCapitals == null)
			return;

		Capital newLocation = locationProvider.getLocation(location, country, currentCapitals);
		if (newLocation == null)
			return;

		List<Capital> newCapitals = new ArrayList<>(currentCapitals);
		newCapitals.add(0, newLocation);
		capitalsLiveData.setValue(newCapitals);
	}

	private double[] getLatLng(CapitalResult capitalResult) {
		double[] latLng = new double[2];
		latLng[0] = isLatLngValid(capitalResult) ? capitalResult.latLng[0] : 0;
		latLng[1] = isLatLngValid(capitalResult) ? capitalResult.latLng[1] : 0;
		return latLng;
	}

	private boolean isLatLngValid(CapitalResult capitalResult) {
		return capitalResult.latLng != null && capitalResult.latLng.length == 2;
	}

	private MutableLiveData<List<Capital>> initCapitalsLiveData() {
		List<Capital> capitals = new ArrayList<>();
		MutableLiveData<List<Capital>> mutableLiveData = new MutableLiveData<>();
		mutableLiveData.setValue(capitals);
		return mutableLiveData;
	}
}
