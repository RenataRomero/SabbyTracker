package com.example.muertedecuna.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.muertedecuna.ActivityMain;
import com.example.muertedecuna.R;
import com.example.muertedecuna.tools.Constant;

public class MyNotificationManager {

    private Context myContext;
    private static MyNotificationManager myInstance;

    public MyNotificationManager(Context myContext) {
        this.myContext = myContext;
    }

    public static synchronized MyNotificationManager getMyInstance(Context context) {
        if(myInstance == null){
            myInstance = new MyNotificationManager(context);
        }

        return myInstance;
    }

    public void displayNotification(String title, String body){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(myContext, Constant.CHANNEL_ID)
                .setSmallIcon(R.drawable.maternity)
                .setContentTitle(title)
                .setContentText(body);


        Intent intent = new Intent(myContext, ActivityMain.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(myContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager myNotificationManager = (NotificationManager) myContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if(myNotificationManager != null){
            myNotificationManager.notify(1, mBuilder.build());
        }
    }

}
