package com.giora.climasale.features.capitalList.domain;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public interface ICapitalsRepository {

	@NonNull
	LiveData<List<Capital>> getCapitals(CapitalProperty[] capitalProperties);

	void addLocation(String location, String country);
}
