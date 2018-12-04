package com.giora.climasale.features.capitalList.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CapitalsLiveApi implements ICapitalsLiveApi {

	private final ICapitalsRemoteApi capitalsRemoteApi;

	public CapitalsLiveApi(ICapitalsRemoteApi capitalsRemoteApi) {
		this.capitalsRemoteApi = capitalsRemoteApi;
	}

	@NonNull
	@Override
	public LiveData<List<CapitalResult>> getCapitals(String fields) {
		final MutableLiveData<List<CapitalResult>> capitalResults = new MutableLiveData<>();
		capitalsRemoteApi.getCapitals(fields).enqueue(new Callback<List<CapitalResult>>() {
			@Override
			public void onResponse(@NonNull Call<List<CapitalResult>> call, @NonNull Response<List<CapitalResult>> response) {
				if (!response.isSuccessful() || response.body() == null)
					return;

				List<CapitalResult> result = response.body();
				capitalResults.setValue(result);
			}

			@Override
			public void onFailure(@NonNull Call<List<CapitalResult>> call, @NonNull Throwable t) {

			}
		});

		return capitalResults;
	}
}
