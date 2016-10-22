package com.speedy.ui.login;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.speedy.R;

public class OnboardingActivity extends AppCompatActivity {
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager            mViewPager;

	public static class OnboardingStep1Fragment extends Fragment {
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
			return inflater.inflate(R.layout.fragment_onboarding_step_1, container, false);
		}
	}

	public static class OnboardingStep2Fragment extends Fragment {
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
			return inflater.inflate(R.layout.fragment_onboarding_step_2, container, false);
		}
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		public SectionsPagerAdapter (FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem (int position) {
			switch (position) {
				case 0:
					return OnboardingStep1Fragment.newInstance();
				case 1:
					return OnboardingStep2Fragment.newInstance();
				default:
					return null;
			}
		}

		@Override
		public int getCount () {
			return 2;
		}

		@Override
		public CharSequence getPageTitle (int position) {
			switch (position) {
				case 0:
					return "Page 1";
				case 1:
					return "Page 2";
				default:
					return null;
			}
		}
	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onboarding);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}
		});
	}
}
