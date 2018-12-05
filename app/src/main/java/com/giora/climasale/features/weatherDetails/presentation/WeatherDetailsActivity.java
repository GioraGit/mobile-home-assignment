package com.giora.climasale.features.weatherDetails.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.giora.climasale.R;
import com.giora.climasale.features.capitalList.presentation.CapitalViewModel;
import com.giora.climasale.services.ioc.component.ComponentManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherDetailsActivity extends AppCompatActivity {

	public final static String CAPITAL_INTENT_EXTRA_KEY = "capitalIntentExtraKey";

	@BindView(R.id.list)
	RecyclerView forecastList;

	@Inject
	IWeatherDetailsViewModelFactory weatherDetailsViewModelFactory;

	private GoogleMap googleMap;
	private WeatherDetailsViewModel weatherDetailsViewModel;
	private CapitalViewModel capitalViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_weather_details);
		ButterKnife.bind(this);
		ComponentManager.getComponent().inject(this);
		weatherDetailsViewModel = ViewModelProviders.of(this, weatherDetailsViewModelFactory).get(WeatherDetailsViewModel.class);

		setCapitalViewModel();
		if (capitalViewModel == null)
			return;

		initMap();
		getForecasts();
	}

	private void setCapitalViewModel() {
		Intent intent = getIntent();
		String capitalViewModelAsJsonString = intent.getStringExtra(CAPITAL_INTENT_EXTRA_KEY);
		if (capitalViewModelAsJsonString == null)
			return;

		capitalViewModel = new Gson().fromJson(capitalViewModelAsJsonString, CapitalViewModel.class);
	}

	private void initMap() {
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		if (mapFragment == null)
			return;

		mapFragment.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(GoogleMap gMap) {
				googleMap = gMap;
				LatLng location = new LatLng(capitalViewModel.getLat(), capitalViewModel.getLng());
				googleMap.addMarker(new MarkerOptions().position(location).title(capitalViewModel.getMarkerText()));
				googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
			}
		});
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
