package com.speedy.wear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OnboardingStep1Activity extends Activity {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onboarding_step1);

		final EditText nameEditText = (EditText) findViewById(R.id.person_name);
		Button         submitButton = (Button) findViewById(R.id.submit_button);

		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View view) {
				if (nameEditText.getText() != null && nameEditText.getText().length() > 0) {
					startActivity(new Intent(OnboardingStep1Activity.this, OnboardingStep2Activity.class));
				}
				else { Toast.makeText(getApplicationContext(), "Please provide a name", Toast.LENGTH_SHORT).show(); }
			}
		});
	}
}
