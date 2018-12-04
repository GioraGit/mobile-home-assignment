package com.giora.climasale.features.capitalList.presentation;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giora.climasale.R;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import butterknife.BindView;
import butterknife.ButterKnife;

class CapitalViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.city)
	TextView cityTextView;

	@BindView(R.id.country)
	TextView countryTextView;

	@BindView(R.id.flag)
	ImageView flagImageView;

	private final Activity containingActivity;

	CapitalViewHolder(@NonNull View itemView,
					  Activity containingActivity) {
		super(itemView);
		this.containingActivity = containingActivity;

		ButterKnife.bind(this, itemView);
	}

	void setCapital(CapitalViewModel capitalViewModel) {
		cityTextView.setText(capitalViewModel.getCity());
		countryTextView.setText(capitalViewModel.getCountry());
		loadFlagImage(capitalViewModel.flagImageUrl);
	}

	private void loadFlagImage(String flagImageUrl) {
		if (flagImageUrl == null || flagImageUrl.isEmpty())
			return;

		GlideToVectorYou.init()
				.with(containingActivity)
				.setPlaceHolder(R.drawable.placeholder_flag, R.drawable.placeholder_flag)
				.load(Uri.parse(flagImageUrl), flagImageView);
	}
}
