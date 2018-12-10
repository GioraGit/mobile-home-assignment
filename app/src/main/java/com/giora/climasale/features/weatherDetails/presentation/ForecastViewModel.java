package com.giora.climasale.features.weatherDetails.presentation;

import android.support.annotation.DrawableRes;

class ForecastViewModel {

	@DrawableRes int dayOfTheWeekImage;

	private String statusText;
	private boolean shouldDisplayStatusText;

	private String date;
	private String minTemperature;
	private String maxTemperature;
	private String precipitation;

	public void setStatusText(String statusText) {
		this.statusText = statusText;
		shouldDisplayStatusText = true;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setDate(String date) {
		this.date = date;
		shouldDisplayStatusText = false;
	}

	public String getDate() {
		return date;
	}

	public void setMaxTemperature(String maxTemperature) {
		this.maxTemperature = maxTemperature;
		shouldDisplayStatusText = false;
	}

	public String getMaxTemperature() {
		return maxTemperature;
	}

	public void setMinTemperature(String minTemperature) {
		this.minTemperature = minTemperature;
		shouldDisplayStatusText = false;
	}

	public String getMinTemperature() {
		return minTemperature;
	}

	public boolean shouldDisplayStatusText() {
		return shouldDisplayStatusText;
	}

	public String getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
		shouldDisplayStatusText = false;
	}
}
