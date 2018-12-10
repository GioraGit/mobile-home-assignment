package com.giora.climasale.features.addPlace.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.giora.climasale.R;
import com.giora.climasale.features.capitalList.presentation.CapitalListViewModel;
import com.giora.climasale.features.capitalList.presentation.ICapitalListViewModelFactory;
import com.giora.climasale.services.ioc.component.ComponentManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPlaceActivity extends AppCompatActivity {

	@Inject
	ICapitalListViewModelFactory capitalListViewModelFactory;

	@BindView(R.id.location)
	TextView locationTextView;

	@BindView(R.id.country)
	TextView countryTextView;

	@BindView(R.id.add_button)
	Button addButton;

	private CapitalListViewModel addPlaceViewModel;
	private TextWatcher textViewsWatcher = createTextViewsWatcher();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_place);
		ButterKnife.bind(this);

		ComponentManager.getComponent().inject(this);
		addPlaceViewModel = ViewModelProviders.of(this, capitalListViewModelFactory).get(CapitalListViewModel.class);

		initTextViews();
		initAddButton();
	}

	private void initAddButton() {
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addPlaceViewModel.addLocation(locationTextView.getText().toString(),
						countryTextView.getText().toString());
				finish();
			}
		});
	}

	private void initTextViews() {
		locationTextView.addTextChangedListener(textViewsWatcher);
		countryTextView.addTextChangedListener(textViewsWatcher);
	}

	private TextWatcher createTextViewsWatcher() {
		return new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (locationTextView.getText().toString().isEmpty() || countryTextView.getText().toString().isEmpty())
					addButton.setEnabled(false);
				else
					addButton.setEnabled(true);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		};
	}
}
