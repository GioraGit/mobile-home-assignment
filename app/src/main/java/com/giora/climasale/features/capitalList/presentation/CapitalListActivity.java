package com.giora.climasale.features.capitalList.presentation;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.giora.climasale.R;
import com.giora.climasale.services.ioc.component.ComponentManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CapitalListActivity extends AppCompatActivity {

	@BindView(R.id.list)
	RecyclerView capitalList;

	@BindView(R.id.progressBar)
	ProgressBar progressBar;

	@Inject
	ICapitalListViewModelFactory capitalListViewModelFactory;

	private CapitalListViewModel capitalListViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_capital_list);
		ButterKnife.bind(this);
		ComponentManager.getComponent().inject(this);
		capitalListViewModel = ViewModelProviders.of(this, capitalListViewModelFactory).get(CapitalListViewModel.class);

		loadCapitals();
	}

	private void loadCapitals() {
		final CapitalListAdapter capitalListAdapter = new CapitalListAdapter();
		initRecyclerView(capitalListAdapter);

		capitalListViewModel.getCapitals().observe(this, new Observer<List<CapitalViewModel>>() {
			@Override
			public void onChanged(@Nullable List<CapitalViewModel> capitalViewModels) {
				capitalListAdapter.setCapitalViewModels(capitalViewModels);
			}
		});

		capitalListAdapter.aViewHolderWasPopulated.observe(this, new Observer<Boolean>() {
			@Override
			public void onChanged(@Nullable Boolean aViewHolderWasPopulated) {
				if (aViewHolderWasPopulated == null || !aViewHolderWasPopulated)
					return;

				resetProgressBar();
			}
		});
	}

	private void initRecyclerView(CapitalListAdapter capitalListAdapter) {
		capitalList.setLayoutManager(new LinearLayoutManager(this));
		capitalList.setHasFixedSize(true);
		capitalList.setAdapter(capitalListAdapter);
	}

	private void resetProgressBar() {
		progressBar.setVisibility(View.GONE);
	}
}
