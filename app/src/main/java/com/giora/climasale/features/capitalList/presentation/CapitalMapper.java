package com.giora.climasale.features.capitalList.presentation;

import com.giora.climasale.features.capitalList.domain.Capital;

public class CapitalMapper implements ICapitalMapper {
	@Override
	public CapitalViewModel map(Capital capital) {
		CapitalViewModel capitalViewModel = new CapitalViewModel();
		capitalViewModel.city = capital.city;
		capitalViewModel.country = capital.country;
		return capitalViewModel;
	}
}
