package com.speedy.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.speedy.R;
import com.speedy.app.PrefsKeys;
import com.speedy.app.Utils;
import com.speedy.ui.race.RaceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Joshua King on 10/22/16.
 */
public class OnboardingStep1Fragment extends Fragment {
	@BindView (R.id.person_name)   TextView personName;
	@BindView (R.id.submit_button) Button   submitButton;
	private                        View     mainView;
	@BindView (R.id.distances_spinner) Spinner distancesSpinner;
	@BindView (R.id.medium_spinner)           Spinner mediumSpinner;
	@BindView (R.id.number_of_people_spinner) Spinner numberOfPeopleSpinner;
	private OnboardingModel model = new OnboardingModel();
	private Unbinder unbinder;

	public OnboardingStep1Fragment () {
	}

	public static OnboardingStep1Fragment newInstance () {
		OnboardingStep1Fragment fragment = new OnboardingStep1Fragment();
		Bundle                  args     = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mainView = inflater.inflate(R.layout.fragment_onboarding_step_1, container, false);
		unbinder = ButterKnife.bind(this, mainView);


		Resources resources         = getContext().getResources();
		final String[]                 distances         = resources.getStringArray(R.array.distances);
		final String[]                 mediums           = resources.getStringArray(R.array.medium);
		final String[]                 numberOfPeople    = resources.getStringArray(R.array.number_of_people);
		SharedPreferences              sharedPreferences = getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
		final SharedPreferences.Editor editor            = sharedPreferences.edit();

		distancesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
				Utils.snackbar(view, String.format("Distance: %s", distances[position]));
				editor.putInt(PrefsKeys.KEY_DISTANCE, position);
				editor.apply();
			}

			@Override
			public void onNothingSelected (AdapterView<?> parent) { }
		});
		mediumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
				Utils.snackbar(view, String.format("Medium: %s", mediums[position]));
				editor.putInt(PrefsKeys.KEY_TYPE, position);
				editor.apply();
			}

			@Override
			public void onNothingSelected (AdapterView<?> parent) { }
		});
		numberOfPeopleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
				Utils.snackbar(view, String.format("Number of People: %s", numberOfPeople[position]));
				editor.putInt(PrefsKeys.KEY_MEMBERS, position);
				editor.apply();
			}

			@Override
			public void onNothingSelected (AdapterView<?> parent) { }
		});

		return mainView;
	}

	@Override
	public void onDestroyView () {
		super.onDestroyView();
		unbinder.unbind();
	}

	@OnClick(R.id.submit_button)
	public void onSubmitButtonClick(View v){
		String name = personName.getText().toString();
		model.savePlayer(name);

		SharedPreferences sharedPreferences = getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(PrefsKeys.KEY_USERNAME, name);
		editor.apply();

		Utils.hideKeyboard(mainView, getContext());


//		((OnboardingActivity) getActivity()).nextPage();
		((OnboardingActivity) getActivity()).startActivity(new Intent((OnboardingActivity) getActivity(), RaceActivity.class));
	}
}
