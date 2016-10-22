package com.speedy.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;
import com.speedy.R;

/**
 * Created by Joshua King on 10/16/16.
 */
public class NotificationController {
	public static final int PROGRESS_NOTIFICATION_ID = 30;

	private static int generateNotificationId () { return (int) System.currentTimeMillis(); }

	public static void notifyProgress (Context context, int progress) {
		Notification notification = new NotificationCompat.Builder(context).setContentTitle("TITLE")
																		   .setContentText("BODY TEXT")
																		   .setSmallIcon(R.drawable.ic_check)
																		   .setOnlyAlertOnce(true)
																		   .setOngoing(true)
																		   .setProgress(100, progress, false)
																		   .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
																		   .build();
		sendNotification(context, notification, PROGRESS_NOTIFICATION_ID);
	}

	private static void sendNotification (Context context, Notification notification, int id) {
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(id, notification);
	}
}
