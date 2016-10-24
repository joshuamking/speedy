package com.speedy.wear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.wearable.view.WearableListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class OnboardingStep4Activity extends Activity {
	private Map<RecyclerView.ViewHolder, Integer> holderToPositionMap = new HashMap<>();

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onboarding_step4);

		WearableListView listView = (WearableListView) findViewById(R.id.list_view);

		final String[] members = getResources().getStringArray(R.array.number_of_people);

		listView.setAdapter(new RecyclerView.Adapter() {
			@Override
			public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
				return new WearableListView.ViewHolder(View.inflate(getApplicationContext(), R.layout.text_view_layout, null));
			}

			@Override
			public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
				holderToPositionMap.put(holder, position);
				((TextView) holder.itemView).setText(members[position]);
			}

			@Override
			public int getItemCount () {
				return members.length;
			}
		});

		// Set a click listener
		listView.setClickListener(new WearableListView.ClickListener() {
			@Override
			public void onClick (WearableListView.ViewHolder viewHolder) {
				try {
					DataStore.setMembers(holderToPositionMap.get(viewHolder));
				} catch (Exception ignored) { }
				startActivity(new Intent(OnboardingStep4Activity.this, InRaceActivity.class));
			}

			@Override
			public void onTopEmptyRegionClick () {

			}
		});
	}
}
