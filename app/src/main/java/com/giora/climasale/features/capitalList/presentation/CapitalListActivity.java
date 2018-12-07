package com.giora.climasale.features.capitalList.presentation;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

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

	private static final String CAPITAL_LIST_LAYOUT_MANAGER_SAVED_STATE = "capitalListLayoutManagerSavedState";
	final CapitalListAdapter capitalListAdapter = new CapitalListAdapter(this);
	final CapitalListAdapter filteredCapitalsAdapter = new CapitalListAdapter(this);
	private CapitalListViewModel capitalListViewModel;
	private Parcelable capitalListLayoutManagerSavedState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_capital_list);
		ButterKnife.bind(this);
		ComponentManager.getComponent().inject(this);
		capitalListViewModel = ViewModelProviders.of(this, capitalListViewModelFactory).get(CapitalListViewModel.class);

		loadCapitals();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_capital_list, menu);
		initSearchAction(menu);
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		saveCapitalListPosition(outState);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		restoreCapitalListPosition(savedInstanceState);
		super.onRestoreInstanceState(savedInstanceState);
	}

	private void loadCapitals() {
		initRecyclerView(capitalListAdapter);

		capitalListViewModel.getCapitals().observe(this, new Observer<List<CapitalViewModel>>() {
			@Override
			public void onChanged(@Nullable List<CapitalViewModel> capitalViewModels) {
				capitalListAdapter.setCapitalViewModels(capitalViewModels);
				setCapitalListPosition();
			}
		});

		registerActionsOnDataLoaded();
	}

	private void initRecyclerView(CapitalListAdapter capitalListAdapter) {
		capitalList.setLayoutManager(new LinearLayoutManager(this));
		capitalList.setHasFixedSize(true);
		capitalList.setAdapter(capitalListAdapter);
	}

	private void resetProgressBar() {
		progressBar.setVisibility(View.GONE);
	}

	private void initSearchAction(Menu menu) {
		final SearchView searchView = (SearchView) menu.findItem(R.id.search_action).getActionView();
		searchView.setIconifiedByDefault(true);
		searchView.setMaxWidth(Integer.MAX_VALUE);
		searchView.setQueryHint(getString(R.string.search_hint));
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				if (newText.isEmpty()) {
					showCapitalList();
					return false;
				}

				capitalListViewModel.filterCapitals(newText).observe(CapitalListActivity.this, new Observer<List<CapitalViewModel>>() {
					@Override
					public void onChanged(@Nullable List<CapitalViewModel> capitalViewModels) {
						filteredCapitalsAdapter.setCapitalViewModels(capitalViewModels);
						showFilteredCapitals();
					}
				});

				return false;
			}
		});
	}

	private void showFilteredCapitals() {
		if (capitalList.getAdapter() != filteredCapitalsAdapter)
			capitalList.swapAdapter(filteredCapitalsAdapter, true);
	}

	private void showCapitalList() {
		if (capitalList.getAdapter() != capitalListAdapter)
			capitalList.swapAdapter(capitalListAdapter, true);
	}

	private void registerActionsOnDataLoaded() {
		capitalListAdapter.aViewHolderWasPopulated.observe(this, new Observer<Boolean>() {
			@Override
			public void onChanged(@Nullable Boolean aViewHolderWasPopulated) {
				if (aViewHolderWasPopulated == null || !aViewHolderWasPopulated)
					return;

				resetProgressBar();
			}
		});
	}

	private void saveCapitalListPosition(Bundle outState) {
		RecyclerView.LayoutManager layoutManager = capitalList.getLayoutManager();
		if (layoutManager != null)
			outState.putParcelable(CAPITAL_LIST_LAYOUT_MANAGER_SAVED_STATE, layoutManager.onSaveInstanceState());
	}

	private void restoreCapitalListPosition(Bundle savedInstanceState) {
		capitalListLayoutManagerSavedState = savedInstanceState.getParcelable(CAPITAL_LIST_LAYOUT_MANAGER_SAVED_STATE);
	}

	private void setCapitalListPosition() {
		if (capitalListLayoutManagerSavedState == null)
			return;

		RecyclerView.LayoutManager layoutManager = capitalList.getLayoutManager();
		if (layoutManager == null)
			return;

		layoutManager.onRestoreInstanceState(capitalListLayoutManagerSavedState);
	}
}
