package com.giora.climasale.features.weatherDetails.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giora.climasale.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class ForecastViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.day_image)
	ImageView dayImageView;

	@BindView(R.id.initial_text)
	TextView initialTextVew;

	@BindView(R.id.date)
	TextView dateTextView;

	@BindView(R.id.max_temp_label)
	TextView maxTempLabelTextView;

	@BindView(R.id.min_temp_label)
	TextView minTempLabelTextView;

	@BindView(R.id.max_temp_value)
	TextView maxTempValueTextView;

	@BindView(R.id.min_temp_value)
	TextView minTempValueTextView;

	@BindView(R.id.precipitation_label)
	TextView precipitationLabelTextView;

	@BindView(R.id.precipitation_value)
	TextView precipitationValueTextView;

	ForecastViewHolder(@NonNull View itemView) {
		super(itemView);

		ButterKnife.bind(this, itemView);
	}

	void setForecast(ForecastViewModel forecastViewModel) {
		dayImageView.setImageResource(forecastViewModel.dayOfTheWeekImage);

		if (forecastViewModel.shouldDisplayStatusText()) {
			initialTextVew.setText(forecastViewModel.getStatusText());
			hideForecast();
			return;
		}

		dateTextView.setText(forecastViewModel.getDate());
		maxTempValueTextView.setText(forecastViewModel.getMaxTemperature());
		minTempValueTextView.setText(forecastViewModel.getMinTemperature());
		precipitationValueTextView.setText(forecastViewModel.getPrecipitation());
		showForecast();
	}

	private void hideForecast() {
		initialTextVew.setVisibility(View.VISIBLE);
		dateTextView.setVisibility(View.INVISIBLE);
		maxTempLabelTextView.setVisibility(View.INVISIBLE);
		minTempLabelTextView.setVisibility(View.INVISIBLE);
		maxTempValueTextView.setVisibility(View.INVISIBLE);
		minTempValueTextView.setVisibility(View.INVISIBLE);
		precipitationLabelTextView.setVisibility(View.INVISIBLE);
		precipitationValueTextView.setVisibility(View.INVISIBLE);
	}

	private void showForecast() {
		initialTextVew.setVisibility(View.INVISIBLE);
		dateTextView.setVisibility(View.VISIBLE);
		maxTempLabelTextView.setVisibility(View.VISIBLE);
		minTempLabelTextView.setVisibility(View.VISIBLE);
		maxTempValueTextView.setVisibility(View.VISIBLE);
		minTempValueTextView.setVisibility(View.VISIBLE);
		precipitationLabelTextView.setVisibility(View.VISIBLE);
		precipitationValueTextView.setVisibility(View.VISIBLE);
	}
}
