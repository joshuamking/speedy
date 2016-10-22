package com.speedy.ui.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.speedy.R;
import com.speedy.app.Utils;
import com.speedy.ui.race.RaceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnboardingActivity extends AppCompatActivity {
	@BindView (R.id.fab)                   FloatingActionButton fab;
	@BindView (R.id.toolbar)               Toolbar              toolbar;
	@BindView (R.id.onboarding_view_pager) ViewPager            viewPager;
	private                                SectionsPagerAdapter pagerAdapter;

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
		public CharSequence getPageTitle (int position) { return ""; }
	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onboarding);

		ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);


		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {
				fab.setScaleX(positionOffset);
				fab.setScaleY(positionOffset);

				if (positionOffset == 0) {
					fab.setScaleX(position == 0 ? 0 : 1);
					fab.setScaleY(position == 0 ? 0 : 1);
				}
			}

			@Override
			public void onPageSelected (int position) {
			}

			@Override
			public void onPageScrollStateChanged (int state) {
				if (state == ViewPager.SCROLL_STATE_DRAGGING) { Utils.hideKeyboard(viewPager, getApplicationContext()); }
			}
		});

		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View view) {
				final ProgressDialog progressDialog = ProgressDialog.show(OnboardingActivity.this, null, "Finding other racers...", true);
				Utils.delayRunnableOnUI(2000, new Runnable() {
					@Override
					public void run () {
						progressDialog.dismiss();
						//						NotificationController.notifyProgress(getApplicationContext());
						startActivity(new Intent(getApplicationContext(), RaceActivity.class));
					}
				});
			}
		});
	}

	public void nextPage(){
		viewPager.setCurrentItem(1, true);
	}
}
