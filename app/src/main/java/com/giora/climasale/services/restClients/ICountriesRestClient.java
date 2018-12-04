package com.giora.climasale.services.restClients;

import android.support.annotation.NonNull;

public interface ICountriesRestClient {
	<T> T createApi(@NonNull Class<T> service);
}
