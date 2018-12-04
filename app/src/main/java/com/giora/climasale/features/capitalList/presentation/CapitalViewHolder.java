package com.giora.climasale.features.capitalList.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.giora.climasale.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class CapitalViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.city)
	TextView cityTextView;

	@BindView(R.id.country)
	TextView countryTextView;

	CapitalViewHolder(@NonNull View itemView) {
		super(itemView);

		ButterKnife.bind(this, itemView);
	}

	void setCapital(CapitalViewModel capitalViewModel) {
		cityTextView.setText(capitalViewModel.getCity());
		countryTextView.setText(capitalViewModel.getCountry());
	}
}
