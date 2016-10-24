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

public class OnboardingStep2Activity extends Activity {
	private Map<RecyclerView.ViewHolder, Integer> holderToPositionMap = new HashMap<>();

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onboarding_step2);

		final WearableListView listView = (WearableListView) findViewById(R.id.list_view);

		final String[] distances = getResources().getStringArray(R.array.distances);

		listView.setAdapter(new RecyclerView.Adapter() {
			@Override
			public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
				return new WearableListView.ViewHolder(View.inflate(getApplicationContext(), R.layout.text_view_layout, null));
			}

			@Override
			public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
				holderToPositionMap.put(holder, position);
				((TextView) holder.itemView).setText(distances[position]);
			}

			@Override
			public int getItemCount () {
				return distances.length;
			}
		});

		listView.setClickListener(new WearableListView.ClickListener() {
			@Override
			public void onClick (WearableListView.ViewHolder viewHolder) {
				try {
					DataStore.setDistance(holderToPositionMap.get(viewHolder));
				} catch (Exception ignored) { }
				startActivity(new Intent(OnboardingStep2Activity.this, OnboardingStep3Activity.class));
			}

			@Override
			public void onTopEmptyRegionClick () {

			}
		});
	}
}
