package com.speedy.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Joshua King on 10/22/16.
 */
public class Utils {
	public static void delayRunnableOnUI (final long millsToDelay, final Runnable runnableToRun) {
		new Thread(new Runnable() {
			public void run () {
				try {
					Thread.sleep(millsToDelay);
				} catch (InterruptedException ignored) {}

				(new Handler(Looper.getMainLooper())).post(runnableToRun);
			}
		}).start();
	}

	public static SharedPreferences getPrefs (Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
	}

	public static void hideKeyboard (View parentViewToGetFocus, Context context) {
		View view = parentViewToGetFocus.findFocus();
		if (view != null) {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	public static boolean isNullOrEmpty (String s) {
		return s == null || s.equals("");
	}

	public static void runOnUI (Runnable runnable) {
		delayRunnableOnUI(0, runnable);
	}

	public static void showKeyboard (View parentViewToGetFocus, Context context) {
		View view = parentViewToGetFocus.findFocus();
		if (view != null) {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
			imm.showSoftInput(view, 0);
		}
	}

	public static void snackbar (View view, String s) {snackbar(view, s, Snackbar.LENGTH_LONG);}

	public static void snackbar (View view, String s, int length) {
		if (view != null) { Snackbar.make(view, s, length).show(); }
	}

	public static void toast (Context context, String s) { toast(context, s, true); }

	public static void toast (Context context, String s, boolean longLength) {
		if (context != null) { Toast.makeText(context, s, longLength ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show(); }
	}
}
