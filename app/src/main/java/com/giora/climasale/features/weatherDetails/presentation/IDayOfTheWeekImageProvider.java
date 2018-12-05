package com.giora.climasale.features.weatherDetails.presentation;

import android.support.annotation.DrawableRes;

public interface IDayOfTheWeekImageProvider {
	@DrawableRes int provideImage(int dayIndex);
}
