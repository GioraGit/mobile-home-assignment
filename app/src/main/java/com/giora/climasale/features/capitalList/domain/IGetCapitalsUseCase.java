package com.giora.climasale.features.capitalList.domain;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public interface IGetCapitalsUseCase {

	@NonNull
	LiveData<List<Capital>> getCapitals();

	@NonNull
	LiveData<List<Capital>> filterCapitals(String term);

	void addLocation(String location, String country);
}
