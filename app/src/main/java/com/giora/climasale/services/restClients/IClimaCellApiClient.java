package com.giora.climasale.services.restClients;

import android.support.annotation.NonNull;

public interface IClimaCellApiClient {
	<T> T createApi(@NonNull Class<T> service);
}
