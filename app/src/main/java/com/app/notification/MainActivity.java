package com.app.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "101";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNotification();
    }
    private void showNotification() {
        createNotificationChannel();

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getActivity(
                MainActivity.this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_MUTABLE);

        Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
        intent1.putExtra("yes", true);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent1;
        pendingIntent1 = PendingIntent.getActivity(
                MainActivity.this, 1, intent1, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_MUTABLE);


        Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
        intent2.putExtra("no", false);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent2;
        pendingIntent2 = PendingIntent.getActivity(
                MainActivity.this, 2, intent2, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_MUTABLE);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Request");
        builder.setContentText("Do you want to accept the request..?");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setSound(uri);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        builder.addAction(R.drawable.ic_launcher_foreground, "yes", pendingIntent1);
        builder.addAction(R.drawable.ic_launcher_foreground, "no", pendingIntent2);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            CharSequence name = "My Notification";
            String description = "my notification description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}