package com.giora.climasale.features.capitalList.presentation;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giora.climasale.R;

import java.util.ArrayList;
import java.util.List;

class CapitalListAdapter extends RecyclerView.Adapter<CapitalViewHolder>{

	final MutableLiveData<Boolean> aViewHolderWasPopulated = new MutableLiveData<>();
	private final Activity containingActivity;
	private List<CapitalViewModel> capitalViewModels;

	public CapitalListAdapter(Activity containingActivity) {
		this.containingActivity = containingActivity;
		capitalViewModels = new ArrayList<>();

		aViewHolderWasPopulated.setValue(false);
	}

	@NonNull
	@Override
	public CapitalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View itemView = LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.view_holder_capital, viewGroup, false);
		return new CapitalViewHolder(itemView, containingActivity);
	}

	@Override
	public void onBindViewHolder(@NonNull CapitalViewHolder capitalViewHolder, int i) {
		capitalViewHolder.setCapital(capitalViewModels.get(i));
		markViewHolderWasPopulated();
	}

	@Override
	public int getItemCount() {
		return capitalViewModels.size();
	}

	void setCapitalViewModels(List<CapitalViewModel> capitalViewModels) {
		this.capitalViewModels = capitalViewModels;
		notifyDataSetChanged();
	}

	private void markViewHolderWasPopulated() {
		Boolean value = aViewHolderWasPopulated.getValue();
		if (value != null && value)
			return;

		aViewHolderWasPopulated.setValue(true);
	}
}
