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

	ForecastViewHolder(@NonNull View itemView) {
		super(itemView);

		ButterKnife.bind(this, itemView);
	}

	void setForecast(ForecastViewModel forecastViewModel) {
		dayImageView.setImageResource(forecastViewModel.dayOfTheWeekImage);
		initialTextVew.setText(forecastViewModel.initialText);
	}
}
