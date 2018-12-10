package com.giora.climasale.features.capitalList.presentation;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.giora.climasale.features.capitalList.domain.Capital;
import com.giora.climasale.features.capitalList.domain.IGetCapitalsUseCase;

import java.util.ArrayList;
import java.util.List;

public class CapitalListViewModel extends ViewModel {

	private final IGetCapitalsUseCase getCapitalsUseCase;
	private final ICapitalMapper capitalMapper;

	CapitalListViewModel(IGetCapitalsUseCase getCapitalsUseCase,
								ICapitalMapper capitalMapper) {
		this.getCapitalsUseCase = getCapitalsUseCase;
		this.capitalMapper = capitalMapper;
	}

	@NonNull
	public LiveData<List<CapitalViewModel>> getCapitals() {
		LiveData<List<Capital>> capitals = getCapitalsUseCase.getCapitals();
		return mapToViewModels(capitals);
	}

	@NonNull
	LiveData<List<CapitalViewModel>> filterCapitals(String term) {
		LiveData<List<Capital>> capitals = getCapitalsUseCase.filterCapitals(term);
		return mapToViewModels(capitals);
	}

	@NonNull
	private LiveData<List<CapitalViewModel>> mapToViewModels(LiveData<List<Capital>> capitals) {
		return Transformations.map(capitals, new Function<List<Capital>, List<CapitalViewModel>>() {
			@Override
			public List<CapitalViewModel> apply(List<Capital> input) {
				List<CapitalViewModel> capitalViewModels = new ArrayList<>();
				for (Capital capital : input)
					capitalViewModels.add(capitalMapper.map(capital));

				return capitalViewModels;
			}
		});
	}
}
