package com.giora.climasale.features.weatherDetails.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.giora.climasale.R;
import com.giora.climasale.services.ioc.component.ComponentManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherDetailsActivity extends AppCompatActivity {

	@BindView(R.id.list)
	RecyclerView forecastList;

	@Inject
	IWeatherDetailsViewModelFactory weatherDetailsViewModelFactory;

	private WeatherDetailsViewModel weatherDetailsViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_weather_details);
		ButterKnife.bind(this);
		ComponentManager.getComponent().inject(this);
		weatherDetailsViewModel = ViewModelProviders.of(this, weatherDetailsViewModelFactory).get(WeatherDetailsViewModel.class);

		getForecasts();
	}

	private void getForecasts() {
		ForecastListAdapter forecastListAdapter = new ForecastListAdapter(weatherDetailsViewModel.getInitialForecasts());
		initRecyclerView(forecastListAdapter);
	}

	private void initRecyclerView(ForecastListAdapter forecastListAdapter) {
		forecastList.setLayoutManager(new LinearLayoutManager(this));
		forecastList.setHasFixedSize(true);
		forecastList.setAdapter(forecastListAdapter);
	}

}
