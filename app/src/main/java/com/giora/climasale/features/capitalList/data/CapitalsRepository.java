package com.giora.climasale.features.capitalList.data;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.giora.climasale.features.capitalList.domain.Capital;
import com.giora.climasale.features.capitalList.domain.CapitalProperty;
import com.giora.climasale.features.capitalList.domain.ICapitalsRepository;

import java.util.ArrayList;
import java.util.List;


public class CapitalsRepository implements ICapitalsRepository {

	private final ICapitalsLiveApi capitalsLiveApi;
	private final ICapitalFieldsParameterBuilder capitalFieldsParameterBuilder;
	private LiveData<List<Capital>> capitals;

	public CapitalsRepository(ICapitalsLiveApi capitalsLiveApi,
							  ICapitalFieldsParameterBuilder capitalFieldsParameterBuilder) {
		this.capitalsLiveApi = capitalsLiveApi;
		this.capitalFieldsParameterBuilder = capitalFieldsParameterBuilder;
	}

	@NonNull
	@Override
	public LiveData<List<Capital>> getCapitals(CapitalProperty[] capitalProperties) {
		if (capitals != null)
			return capitals;

		LiveData<List<CapitalResult>> capitalResults = capitalsLiveApi.getCapitals(capitalFieldsParameterBuilder.build(capitalProperties));

		capitals = Transformations.map(capitalResults, new Function<List<CapitalResult>, List<Capital>>() {
			@Override
			public List<Capital> apply(List<CapitalResult> input) {
				List<Capital> capitals = new ArrayList<>();
				for (CapitalResult capitalResult : input) {
					double[] latLng = getLatLng(capitalResult);
					capitals.add(new Capital(capitalResult.capital, capitalResult.country,
							capitalResult.flag, latLng[0], latLng[1]));
				}

				return capitals;
			}
		});

		return capitals;
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
}
