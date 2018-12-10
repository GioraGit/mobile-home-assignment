package com.giora.climasale.features.addPlace.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.giora.climasale.features.capitalList.domain.Capital;

import java.util.List;

public class LocationProvider implements ILocationProvider {
	@Nullable
	@Override
	public Capital getLocation(String location, String country, @NonNull List<Capital> capitalsReference) {
		String flagImageUrl = null;
		for (Capital capital : capitalsReference) {
			if (locationsAreTheSame(location, country, capital))
				return null;

			if (capital.country.toLowerCase().equals(country.toLowerCase())) {
				flagImageUrl = capital.flagImageUrl;
				break;
			}
		}

		return new Capital(location, country, flagImageUrl, null, null);
	}

	private boolean locationsAreTheSame(String location, String country, Capital capital) {
		return location.toLowerCase().equals(capital.city.toLowerCase())
				&& country.toLowerCase().equals(capital.country.toLowerCase());
	}
}
