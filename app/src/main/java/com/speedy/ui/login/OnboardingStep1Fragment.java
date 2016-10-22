package com.speedy.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.speedy.R;
import com.speedy.app.PrefsKeys;
import com.speedy.app.Utils;

/**
 * Created by Joshua King on 10/22/16.
 */
public class OnboardingStep1Fragment extends Fragment {
	@BindView (R.id.person_name)   TextView personName;
	@BindView (R.id.submit_button) Button   submitButton;
	private                        View     mainView;
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

		((OnboardingActivity) getActivity()).nextPage();
	}
}
