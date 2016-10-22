package com.speedy.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.speedy.R;
import com.speedy.api.FireBase;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

	@BindView(R.id.button) Button button;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		ButterKnife.bind(this);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new FireBase().connect();
			}
		});

	}
}
