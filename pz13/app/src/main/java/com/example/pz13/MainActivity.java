package com.example.pz13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View v) {
        Intent i = new Intent(this, MediaService.class);

        if (v.getId() == R.id.start) {
            startService(i);
        } else if (v.getId() == R.id.stop) {
            stopService(i);
        } else if (v.getId() == R.id.pause) {
            i.setAction("PAUSE");
            startService(i);
        } else if (v.getId() == R.id.resume) {
            i.setAction("RESUME");
            startService(i);
        }
    }
}
