package com.speedy.ui;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Joshua King on 10/22/16.
 */
public class UiUtils {
	public static void snackbar (View view, String s) {snackbar(view, s, Snackbar.LENGTH_LONG);}

	public static void snackbar (View view, String s, int length) {
		if (view != null) { Snackbar.make(view, s, length).show(); }
	}

	public static void toast (Context context, String s) { toast(context, s, true); }

	public static void toast (Context context, String s, boolean longLength) {
		if (context != null) { Toast.makeText(context, s, longLength ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show(); }
	}
}
