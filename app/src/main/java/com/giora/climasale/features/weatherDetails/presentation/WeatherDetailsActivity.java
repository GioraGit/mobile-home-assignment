package com.giora.climasale.features.weatherDetails.presentation;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.giora.climasale.R;
import com.giora.climasale.features.capitalList.presentation.CapitalViewModel;
import com.giora.climasale.services.ioc.component.ComponentManager;
import com.giora.climasale.services.location.ILatLngProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherDetailsActivity extends AppCompatActivity {

	public final static String CAPITAL_INTENT_EXTRA_KEY = "capitalIntentExtraKey";
	private static final int MAP_ZOOM_LEVEL = 10;

	@BindView(R.id.list)
	RecyclerView forecastList;

	@Inject
	IWeatherDetailsViewModelFactory weatherDetailsViewModelFactory;

	@Inject
	ILatLngProvider latLngProvider;

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

		getForecasts();
	}

	private void setCapitalViewModel() {
		Intent intent = getIntent();
		String capitalViewModelAsJsonString = intent.getStringExtra(CAPITAL_INTENT_EXTRA_KEY);
		if (capitalViewModelAsJsonString == null)
			return;

		capitalViewModel = new Gson().fromJson(capitalViewModelAsJsonString, CapitalViewModel.class);
	}

	private void getForecasts() {
		final ForecastListAdapter forecastListAdapter = new ForecastListAdapter(weatherDetailsViewModel.getInitialForecasts());
		initRecyclerView(forecastListAdapter);

		latLngProvider.getForAddress(this, capitalViewModel.getMarkerAddress())
				.observe(this, new Observer<LatLng>() {
					@Override
					public void onChanged(@Nullable LatLng latLng) {
						if (latLng == null)
							return;

						initMap(latLng);
						weatherDetailsViewModel.getForecasts(latLng)
								.observe(WeatherDetailsActivity.this, new Observer<List<ForecastViewModel>>() {
									@Override
									public void onChanged(@Nullable List<ForecastViewModel> forecastViewModels) {
										forecastListAdapter.setForecastViewModels(forecastViewModels);
									}
								});
					}
				});
	}

	private void initMap(final LatLng latLng) {
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		if (mapFragment == null)
			return;

		mapFragment.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(GoogleMap googleMap) {
				googleMap.addMarker(new MarkerOptions().position(latLng).title(capitalViewModel.getMarkerAddress()));
				googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, MAP_ZOOM_LEVEL));
			}
		});
	}

	private void initRecyclerView(ForecastListAdapter forecastListAdapter) {
		forecastList.setLayoutManager(new LinearLayoutManager(this));
		forecastList.setHasFixedSize(true);
		forecastList.setAdapter(forecastListAdapter);
	}

}
