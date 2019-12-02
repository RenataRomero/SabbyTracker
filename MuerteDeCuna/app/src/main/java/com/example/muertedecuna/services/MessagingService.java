package com.example.muertedecuna.services;

import android.util.Log;

import com.example.muertedecuna.notifications.MyNotificationManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MessagingService extends FirebaseMessagingService {
    public MessagingService() {
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.e("MYFIREBASETOKEN", "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {




        Log.d("NOTIFICATION", "From: " + remoteMessage.getFrom());



        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("NOTIFICATION", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

            MyNotificationManager.getMyInstance(getApplicationContext()).displayNotification(title,body);

        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}
