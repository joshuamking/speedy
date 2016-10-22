package com.speedy.ui.login;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.speedy.R;
import com.speedy.ui.UiUtils;

/**
 * Created by Joshua King on 10/22/16.
 */
public class OnboardingStep2Fragment extends Fragment {
	@BindView (R.id.distances_spinner)        Spinner  distancesSpinner;
	@BindView (R.id.medium_spinner)           Spinner  mediumSpinner;
	@BindView (R.id.number_of_people_spinner) Spinner  numberOfPeopleSpinner;
	private                                   Unbinder unbinder;

	public OnboardingStep2Fragment () {
	}

	public static OnboardingStep2Fragment newInstance () {
		OnboardingStep2Fragment fragment = new OnboardingStep2Fragment();
		Bundle                  args     = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_onboarding_step_2, container, false);
		unbinder = ButterKnife.bind(this, view);

		Resources      resources      = getContext().getResources();
		final String[] distances      = resources.getStringArray(R.array.distances);
		final String[] mediums        = resources.getStringArray(R.array.medium);
		final String[] numberOfPeople = resources.getStringArray(R.array.number_of_people);

		distancesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
				UiUtils.snackbar(view, String.format("Distance: %s", distances[position]));
			}

			@Override
			public void onNothingSelected (AdapterView<?> parent) { }
		});
		mediumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
				UiUtils.snackbar(view, String.format("Medium: %s", mediums[position]));
			}

			@Override
			public void onNothingSelected (AdapterView<?> parent) { }
		});
		numberOfPeopleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
				UiUtils.snackbar(view, String.format("Number of People: %s", numberOfPeople[position]));
			}

			@Override
			public void onNothingSelected (AdapterView<?> parent) { }
		});

		return view;
	}

	@Override
	public void onDestroyView () {
		super.onDestroyView();
		unbinder.unbind();
	}
}
