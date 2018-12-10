package com.giora.climasale.features.capitalList.presentation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giora.climasale.R;
import com.giora.climasale.features.weatherDetails.presentation.WeatherDetailsActivity;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.google.gson.Gson;

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
	private CapitalViewModel currentCapitalViewModel;

	CapitalViewHolder(@NonNull View itemView,
					  Activity containingActivity) {
		super(itemView);
		this.containingActivity = containingActivity;

		ButterKnife.bind(this, itemView);
		setOnViewClickListener();
	}

	void setCapital(CapitalViewModel capitalViewModel) {
		currentCapitalViewModel = capitalViewModel;
		cityTextView.setText(capitalViewModel.getCity());
		countryTextView.setText(capitalViewModel.getCountry());
		loadFlagImage(capitalViewModel.flagImageUrl);
	}

	private void setOnViewClickListener() {
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (currentCapitalViewModel == null)
					return;

				startWeatherDetailsActivity();
			}
		});
	}

	private void startWeatherDetailsActivity() {
		Intent weatherDetailsIntent = new Intent(containingActivity.getApplicationContext(), WeatherDetailsActivity.class);
		weatherDetailsIntent.putExtra(WeatherDetailsActivity.CAPITAL_INTENT_EXTRA_KEY, new Gson().toJson(currentCapitalViewModel));
		containingActivity.startActivity(weatherDetailsIntent);
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
