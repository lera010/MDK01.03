package com.example.pz5;

import static android.Manifest.permission.POST_NOTIFICATIONS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static androidx.core.app.NotificationCompat.PRIORITY_HIGH;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnMorning, btnDay,btnEvening, btnNight;
    private ImageView imgMorning, imgDay,imgEvening, imgNight;
    private ConstraintLayout layoutMorning;
    private NotificationManager notificationManager;
    private static final int NOTIFY_ID=1;
    private static final String CHANNEL_ID="CHANNEL_ID";
    private String currentTime = "Покорми меня";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMorning=(Button) findViewById(R.id.buttonMorning);
        btnDay=(Button) findViewById(R.id.buttonDay);
        btnEvening=(Button) findViewById(R.id.buttonEvening);
        btnNight=(Button) findViewById(R.id.buttonNight);

        imgMorning=(ImageView) findViewById(R.id.imageMorning);
        imgDay=(ImageView) findViewById(R.id.imageDay);
        imgEvening=(ImageView) findViewById(R.id.imageEvening);
        imgNight=(ImageView) findViewById(R.id.imageNight);

        layoutMorning = (ConstraintLayout) findViewById(R.id.layoutMorning);
    }

    public void btnMorningClick(View view){
        layoutMorning.setVisibility(View.VISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        showNotification("Утро", "Доброе утро");
        currentTime = "Покорми меня";
    }
    public void btnDayClick(View view){
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.VISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        showNotification("День", "Добрый день");
        currentTime = "Поиграй со мной";
    }
    public void showNotification(String title, String text){
        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setAutoCancel(false)
                        .setSmallIcon(R.drawable.day)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

        createChannelIfNeeded(notificationManager);
        notificationManager.notify(NOTIFY_ID, notificationBuilder.build());
    }

    public static void createChannelIfNeeded(NotificationManager manager){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Channel name",NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }
    public void btnEveningClick(View view){
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.VISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        showNotification("Вечер", "Добрый вечер");
        currentTime = "Погладь меня";
    }
    public void btnNightClick(View view){
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.VISIBLE);

        showNotification("Ночь", "Доброй ночи");
        currentTime = "Пошли спать";
    }

    public void btnNoseClick(View view){
        Toast myToast = Toast.makeText(getApplicationContext(), currentTime, Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.BOTTOM,0,0);
        myToast.show();
    }
}