package com.giora.climasale.features.capitalList.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICapitalsRemoteApi {

	@GET("all")
	Call<List<CapitalResult>> getCapitals(@Query("fields") String fields);
}
