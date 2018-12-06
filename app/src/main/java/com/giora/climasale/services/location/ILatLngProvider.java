package com.giora.climasale.services.location;

import android.app.Activity;
import android.arch.lifecycle.LiveData;

import com.google.android.gms.maps.model.LatLng;

public interface ILatLngProvider {
	LiveData<LatLng> getForAddress(Activity callingActivity, String address);
}
