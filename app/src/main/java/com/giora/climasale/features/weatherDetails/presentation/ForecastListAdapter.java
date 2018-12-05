package com.giora.climasale.features.weatherDetails.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giora.climasale.R;

import java.util.List;

class ForecastListAdapter extends RecyclerView.Adapter<ForecastViewHolder>{

	private final List<ForecastViewModel> forecastViewModels;

	ForecastListAdapter(List<ForecastViewModel> forecastViewModels) {
		this.forecastViewModels = forecastViewModels;
	}

	@NonNull
	@Override
	public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View itemView = LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.view_holder_forecast, viewGroup, false);
		return new ForecastViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull ForecastViewHolder forecastViewHolder, int i) {
		forecastViewHolder.setForecast(forecastViewModels.get(i));
	}

	@Override
	public int getItemCount() {
		return forecastViewModels.size();
	}
}
