package com.speedy.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.NotificationCompat;
import com.speedy.R;
import com.speedy.ui.login.OnboardingActivity;

/**
 * Created by Joshua King on 10/16/16.
 */
public class NotificationController {
	public static final  int    ANNOUNCEMENT_GROUP_ID        = 0;
	public static final  int    ANNOUNCEMENT_NOTIFICATION_ID = 1;
	private static final String NOTIFICATION_GROUP           = "hackGSU-Announcements";
	private static NotificationCompat.Builder announcementBuilder;

	private static PendingIntent createActivityIntent (Context context, Class<?> cls, Bundle extras) {
		Intent intent = new Intent(context, cls);
		if (extras != null) { intent.putExtras(extras); }
		return PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
	}

	private static Notification createAnnouncementsHeader (Context context) {
		return createHeaderBuilder(context, NOTIFICATION_GROUP, R.drawable.ic_check, "New hackGSU Announcements", R.color.colorPrimary).build();
	}

	private static Builder createBuilderForAnnouncement (Context context, int notificationId) {
		Bundle mainActivityExtras = new Bundle();
		//		mainActivityExtras.putSerializable(MainActivity.HIGHLIGHT_ANNOUNCEMENT, announcement);
		final PendingIntent pendingIntent = createActivityIntent(context, OnboardingActivity.class, mainActivityExtras);

		//		Bundle bookmarkExtras = new Bundle();
		//		bookmarkExtras.putBoolean(AnnouncementManipulationService.BOOKMARK_ANNOUNCEMENT, true);
		//		bookmarkExtras.putSerializable(AnnouncementManipulationService.ANNOUNCEMENT, announcement);
		//		bookmarkExtras.putInt(AnnouncementManipulationService.ANNOUNCEMENT_NOTIFICATION_ID_EXTRA, notificationId);
		//		PendingIntent bookmarkPendingIntent = createServiceIntent(context, bookmarkExtras, AnnouncementManipulationService.class);
		//		int           bookmarkIcon          = announcement.isBookmarkedByMe() ? R.drawable.ic_bookmarked : R.drawable.ic_not_bookmarked;
		//		String        bookmarkString        = announcement.isBookmarkedByMe() ? "Unbookmark" : "Bookmark";
		//		Action        bookmarkAction        = new Action(bookmarkIcon, bookmarkString, bookmarkPendingIntent);

		//		Bundle likeExtras = new Bundle();
		//		likeExtras.putBoolean(AnnouncementManipulationService.LIKE_ANNOUNCEMENT, true);
		//		likeExtras.putSerializable(AnnouncementManipulationService.ANNOUNCEMENT, announcement);
		//		likeExtras.putInt(AnnouncementManipulationService.ANNOUNCEMENT_NOTIFICATION_ID_EXTRA, notificationId);
		//		PendingIntent likePendingIntent = createServiceIntent(context, likeExtras, AnnouncementManipulationService.class);
		//		int           likeIcon          = announcement.isLikedByMe() ? R.drawable.ic_heart : R.drawable.ic_empty_heart;
		//		String        likeString        = announcement.isLikedByMe() ? "Unlike" : "Like";
		//		Action        likeAction        = new Action(likeIcon, likeString, likePendingIntent);

		if (announcementBuilder == null) {
			announcementBuilder = new NotificationCompat.Builder(context);
			//			announcementBuilder.addAction(likeAction).addAction(bookmarkAction);
		}
		else if (announcementBuilder.mActions != null) {
			//			announcementBuilder.mActions.clear();
			//			announcementBuilder.mActions.add(likeAction);
			//			announcementBuilder.mActions.add(bookmarkAction);
		}
		return announcementBuilder.setContentTitle("TITLE")
								  .setContentText("BODY TEXT")
								  .setStyle(new NotificationCompat.BigTextStyle().bigText(""))
								  .setSmallIcon(R.drawable.ic_check)
								  .setContentIntent(pendingIntent)
								  .setOnlyAlertOnce(true)
								  .setAutoCancel(true)
								  .setVibrate(new long[] { 0, 100, 300, 300 })
								  //								  .setWhen(announcement.getTimestamp())
								  .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
								  .setPriority(NotificationCompat.PRIORITY_HIGH)
								  .setGroup(NOTIFICATION_GROUP);
	}

	private static Builder createHeaderBuilder (Context context, String notificationGroup, int icon, String title, int color) {
		return new Builder(context).setColor(ContextCompat.getColor(context, color))
								   .setContentTitle(title)
								   .setSmallIcon(icon)
								   .setAutoCancel(true)
								   .setGroupSummary(true)
								   .setContentIntent(createActivityIntent(context, OnboardingActivity.class, null))
								   .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
								   .setPriority(NotificationCompat.PRIORITY_HIGH)
								   .setGroup(notificationGroup);
	}

	private static PendingIntent createServiceIntent (Context context, Bundle extras, Class<?> cls) {
		Intent intent = new Intent(context, cls);
		intent.putExtras(extras);
		return PendingIntent.getService(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
	}

	private static int generateNotificationId () { return (int) System.currentTimeMillis(); }

	public static void sendAnnouncementNotification (Context context) {
		sendAnnouncementNotification(context, generateNotificationId());
	}

	public static void sendAnnouncementNotification (Context context, int notificationId) {
		sendNotification(context, createBuilderForAnnouncement(context, notificationId).build(), ANNOUNCEMENT_GROUP_ID, notificationId);
	}

	/**
	 * This is still only for announcements, any other types of notifications need to generalize this first
	 */
	private static void sendNotification (Context context, Notification notification, int groupId, int id) {
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(groupId, createAnnouncementsHeader(context));
		notificationManager.notify(id, notification);
	}
}
