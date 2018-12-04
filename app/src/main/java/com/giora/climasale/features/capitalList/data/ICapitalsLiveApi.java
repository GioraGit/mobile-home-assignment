package com.giora.climasale.features.capitalList.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public interface ICapitalsLiveApi {

	@NonNull
	LiveData<List<CapitalResult>> getCapitals(String fields);
}
