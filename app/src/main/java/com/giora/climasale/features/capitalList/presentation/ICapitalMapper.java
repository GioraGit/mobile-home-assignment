package com.giora.climasale.features.capitalList.presentation;

import com.giora.climasale.features.capitalList.domain.Capital;

public interface ICapitalMapper {
	CapitalViewModel map(Capital capital);
}
