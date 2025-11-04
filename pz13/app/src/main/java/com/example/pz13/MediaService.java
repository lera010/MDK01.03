package com.example.pz13;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MediaService extends Service {
    MediaPlayer ambientMediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        ambientMediaPlayer = MediaPlayer.create(this, R.raw.testmusic);
        ambientMediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();

            if (ambientMediaPlayer == null) {
                return START_STICKY;
            }

            switch (action) {
                case "PAUSE":
                    if (ambientMediaPlayer.isPlaying()) {
                        ambientMediaPlayer.pause();
                    }
                    break;

                case "RESUME":
                    if (!ambientMediaPlayer.isPlaying()) {
                        ambientMediaPlayer.start();
                    }
                    break;

                default:
                    if (!ambientMediaPlayer.isPlaying()) {
                        ambientMediaPlayer.start();
                    }
                    break;
            }
        } else {
            if (ambientMediaPlayer != null && !ambientMediaPlayer.isPlaying()) {
                ambientMediaPlayer.start();
            }
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (ambientMediaPlayer != null) {
            if (ambientMediaPlayer.isPlaying()) {
                ambientMediaPlayer.stop();
            }
            ambientMediaPlayer.release();
            ambientMediaPlayer = null;
        }
        super.onDestroy();
    }

}

