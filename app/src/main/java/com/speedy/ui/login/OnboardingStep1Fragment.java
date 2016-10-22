package com.speedy.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.speedy.R;

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
	private                        Unbinder unbinder;
	private OnboardingModel model = new OnboardingModel();

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
		View view = inflater.inflate(R.layout.fragment_onboarding_step_1, container, false);
		unbinder = ButterKnife.bind(this, view);
		return view;
	}

	@OnClick(R.id.submit_button)
	public void onSubmitButtonClick(View v){
		model.savePlayer(personName.getText().toString());
	}

	@Override
	public void onDestroyView () {
		super.onDestroyView();
		unbinder.unbind();
	}
}
