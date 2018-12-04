package com.giora.climasale.features.capitalList.presentation;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.giora.climasale.features.capitalList.domain.IGetCapitalsUseCase;

public class CapitalListViewModelFactory implements ICapitalListViewModelFactory {

	private final IGetCapitalsUseCase getCapitalsUseCase;
	private final ICapitalMapper capitalMapper;

	public CapitalListViewModelFactory(IGetCapitalsUseCase getCapitalsUseCase,
								ICapitalMapper capitalMapper) {
		this.getCapitalsUseCase = getCapitalsUseCase;
		this.capitalMapper = capitalMapper;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		if (modelClass == CapitalListViewModel.class)
			return modelClass.cast(new CapitalListViewModel(getCapitalsUseCase, capitalMapper));
		else
			throw new IllegalArgumentException("ViewModel not found");
	}
}
