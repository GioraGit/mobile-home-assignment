package com.giora.climasale.features.addPlace.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.giora.climasale.features.capitalList.domain.Capital;

import java.util.List;

public interface ILocationProvider {

	@Nullable
	Capital getLocation(String location, String country, @NonNull List<Capital> capitalsReference);
}
