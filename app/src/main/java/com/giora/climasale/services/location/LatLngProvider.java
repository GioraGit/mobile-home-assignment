package com.giora.climasale.services.location;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

public class LatLngProvider implements ILatLngProvider {
	@Override
	public LiveData<LatLng> getForAddress(Activity callingActivity, String address) {
		final MutableLiveData<LatLng> latLngLiveData = new MutableLiveData<>();
		new GeocoderTask(callingActivity, address, new IOnLatLngReadyCallback() {
			@Override
			public void onLatLngReady(@NonNull LatLng latLng) {
				latLngLiveData.setValue(latLng);
			}
		}).execute();
		return latLngLiveData;
	}

	private interface IOnLatLngReadyCallback {
		void onLatLngReady(@NonNull LatLng latLng);
	}

	private static class GeocoderTask extends AsyncTask<Void, Void, List<Address>> {

		private final WeakReference<Activity> callingActivityReference;
		private final String locationName;
		private final IOnLatLngReadyCallback onAddressReadyCallback;

		private GeocoderTask(Activity callingActivity, String address, IOnLatLngReadyCallback onAddressReadyCallback) {
			this.callingActivityReference = new WeakReference<>(callingActivity);
			this.locationName = address;
			this.onAddressReadyCallback = onAddressReadyCallback;
		}

		@Override
		protected List<Address> doInBackground(Void... voids) {
			Activity callingActivity = callingActivityReference.get();
			if (callingActivity == null)
				return null;

			Geocoder geocoder = new Geocoder(callingActivity);
			try {
				return geocoder.getFromLocationName(locationName, 1);
			} catch (IOException e) {
				return null;
			}
		}

		@Override
		protected void onPostExecute(List<Address> addresses) {
			if (addresses == null || addresses.isEmpty())
				return;

			Address address = addresses.get(0);
			onAddressReadyCallback.onLatLngReady(new LatLng(address.getLatitude(), address.getLongitude()));
		}
	}
}
