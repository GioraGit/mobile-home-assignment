package com.giora.climasale.features.weatherDetails.presentation;

import android.support.annotation.DrawableRes;

class ForecastViewModel {

	@DrawableRes int dayOfTheWeekImage;

	private String initialText;
	private boolean shouldDisplayInitialText;

	private String date;
	private String minTemperature;
	private String maxTemperature;

	public void setInitialText(String initialText) {
		this.initialText = initialText;
		shouldDisplayInitialText = true;
	}

	public String getInitialText() {
		return initialText;
	}

	public void setDate(String date) {
		this.date = date;
		shouldDisplayInitialText = false;
	}

	public String getDate() {
		return date;
	}

	public void setMaxTemperature(String maxTemperature) {
		this.maxTemperature = maxTemperature;
		shouldDisplayInitialText = false;
	}

	public String getMaxTemperature() {
		return maxTemperature;
	}

	public void setMinTemperature(String minTemperature) {
		this.minTemperature = minTemperature;
		shouldDisplayInitialText = false;
	}

	public String getMinTemperature() {
		return minTemperature;
	}

	public boolean shouldDisplayInitialText() {
		return shouldDisplayInitialText;
	}
}
