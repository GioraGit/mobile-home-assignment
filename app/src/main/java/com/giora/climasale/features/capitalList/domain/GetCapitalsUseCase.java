package com.giora.climasale.features.capitalList.domain;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class GetCapitalsUseCase implements IGetCapitalsUseCase {

	private final ICapitalsRepository capitalsRepository;

	public GetCapitalsUseCase(ICapitalsRepository capitalsRepository) {
		this.capitalsRepository = capitalsRepository;
	}

	@NonNull
	@Override
	public LiveData<List<Capital>> getCapitals() {
		return capitalsRepository.getCapitals(new CapitalProperty[]{CapitalProperty.City,
				CapitalProperty.Country, CapitalProperty.FlagImageUrl, CapitalProperty.LatLng});
	}

	@NonNull
	@Override
	public LiveData<List<Capital>> filterCapitals(String term) {
		List<Capital> allCapitals = getCapitals().getValue();
		if (allCapitals == null)
			return new MutableLiveData<>();

		List<Capital> filteredCapitals = new ArrayList<>();
		for (Capital capital : allCapitals) {
			if (capital.city.toLowerCase().contains(term.toLowerCase())
					|| capital.country.toLowerCase().contains(term.toLowerCase()))
				filteredCapitals.add(capital);
		}

		MutableLiveData<List<Capital>> data = new MutableLiveData<>();
		data.setValue(filteredCapitals);
		return data;
	}

	@Override
	public void addLocation(String location, String country) {
		capitalsRepository.addLocation(location, country);
	}
}
