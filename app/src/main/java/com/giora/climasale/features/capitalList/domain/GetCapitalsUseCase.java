package com.giora.climasale.features.capitalList.domain;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class GetCapitalsUseCase implements IGetCapitalsUseCase {

	private final ICapitalsRepository capitalsRepository;

	public GetCapitalsUseCase(ICapitalsRepository capitalsRepository) {
		this.capitalsRepository = capitalsRepository;
	}

	@NonNull
	@Override
	public LiveData<List<Capital>> getCapitals() {
		return capitalsRepository.getCapitals(new CapitalProperty[]{CapitalProperty.City, CapitalProperty.Country});
	}
}
