package com.giora.climasale.features.seeOnMap.presentation;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.giora.climasale.R;
import com.giora.climasale.features.capitalList.presentation.CapitalListViewModel;
import com.giora.climasale.features.capitalList.presentation.CapitalViewModel;
import com.giora.climasale.features.capitalList.presentation.ICapitalListViewModelFactory;
import com.giora.climasale.services.ioc.component.ComponentManager;
import com.giora.climasale.services.location.ILatLngProvider;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

public class SeeOnMapActivity extends AppCompatActivity {

	@Inject
	ICapitalListViewModelFactory capitalListViewModelFactory;

	@Inject
	ILatLngProvider latLngProvider;

	private CapitalListViewModel seeOnMapViewModel;
	private GoogleMap googleMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_on_map);

		ComponentManager.getComponent().inject(this);
		seeOnMapViewModel = ViewModelProviders.of(this, capitalListViewModelFactory).get(CapitalListViewModel.class);

		initMap();
	}

	private void loadMarkers() {
		seeOnMapViewModel.getCapitals().observe(this, new Observer<List<CapitalViewModel>>() {
			@Override
			public void onChanged(@Nullable List<CapitalViewModel> capitalViewModels) {
				final MarkerOptions markerOptions = new MarkerOptions();
				for (final CapitalViewModel capitalViewModel : capitalViewModels) {
					markerOptions
							.position(new LatLng(capitalViewModel.getLatitude(), capitalViewModel.getLongitude()))
							.title(capitalViewModel.getCountry());
					googleMap.addMarker(markerOptions);

				}
			}
		});
	}

	private void initMap() {
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		if (mapFragment == null)
			return;

		mapFragment.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(GoogleMap googleMap) {
				SeeOnMapActivity.this.googleMap = googleMap;
				loadMarkers();
			}
		});
	}
}
