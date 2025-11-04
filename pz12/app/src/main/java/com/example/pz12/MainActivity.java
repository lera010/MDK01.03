package com.example.pz12;

import android.app.Activity;
import android.content.ContentUris;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.io.IOException;

public class MainActivity extends Activity implements OnPreparedListener,
        OnCompletionListener {

    final String LOG_TAG = "myLogs";

    final String DATA_HTTP = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
    final String DATA_STREAM = "http://stream.radiopotok.ru:8000/potok";
    final String DATA_SD = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
            + "/raw/testmusic.mp3";
    final Uri DATA_URI = Uri.parse("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3");

    MediaPlayer mediaPlayer;
    AudioManager am;
    CheckBox chbLoop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        chbLoop = (CheckBox) findViewById(R.id.chbLoop);
        chbLoop.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (mediaPlayer != null)
                    mediaPlayer.setLooping(isChecked);
            }
        });

    }

    public void onClickStart(View view) {
        releaseMP();

        try {
            if (view.getId() == R.id.btnStartHttp)
            {
                Log.d(LOG_TAG, "start HTTP");
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(DATA_HTTP);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                Log.d(LOG_TAG, "prepareAsync");
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.prepareAsync();
            }
            else if (view.getId() == R.id.btnStartStream)
            {
                Log.d(LOG_TAG, "start Stream");
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(DATA_STREAM);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                Log.d(LOG_TAG, "prepareAsync");
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.prepareAsync();

            }
            else if (view.getId() == R.id.btnStartSD)
            {
                Log.d(LOG_TAG, "start SD");
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(DATA_SD);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
                mediaPlayer.start();

            }
            else if (view.getId() == R.id.btnStartUri)
            {
                Log.d(LOG_TAG, "start Uri");
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(this, DATA_URI);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
                mediaPlayer.start();

            }
            else if (view.getId() == R.id.btnStartRaw)
            {
                Log.d(LOG_TAG, "start Raw");
                mediaPlayer = MediaPlayer.create(this, R.raw.testmusic);
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mediaPlayer == null)
            return;
        mediaPlayer.setLooping(chbLoop.isChecked());
        mediaPlayer.setOnCompletionListener(this);

    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void onClick(View view) {
        if (mediaPlayer == null)
            return;
        else if (view.getId() == R.id.btnPause)
        {
            if (mediaPlayer.isPlaying())
                mediaPlayer.pause();
        }
        else if (view.getId() == R.id.btnResume)
        {
            if (!mediaPlayer.isPlaying())
                mediaPlayer.start();
        }
        else if (view.getId() == R.id.btnStop)
        {
            mediaPlayer.stop();
        }
        else if (view.getId() == R.id.btnBackward)
        {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 3000);
        }
        else if (view.getId() == R.id.btnForward)
        {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 3000);
        }
        else if (view.getId() == R.id.btnInfo)
        {
            Log.d(LOG_TAG, "Playing " + mediaPlayer.isPlaying());
            Log.d(LOG_TAG, "Time " + mediaPlayer.getCurrentPosition() + " / "
                    + mediaPlayer.getDuration());
            Log.d(LOG_TAG, "Looping " + mediaPlayer.isLooping());
            Log.d(LOG_TAG,
                    "Volume " + am.getStreamVolume(AudioManager.STREAM_MUSIC));
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(LOG_TAG, "onPrepared");
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(LOG_TAG, "onCompletion");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();
    }
}
