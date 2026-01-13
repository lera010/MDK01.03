package com.example.pz18;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

import java.text.DateFormat;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        updateWeatherData("Orenburg");
    }
    private void updateWeatherData(final String city) {
        new Thread() {
            public void run() {
                final JSONObject json = ConnectFetch.getJSON(MainActivity.this, city);
                if (json ==
                        null) {
                    handler.post(new
                                         Runnable() {
                                             public void
                                             run() {
                                                 Toast.makeText(MainActivity.this,
                                                         city + "-информация не найдена",
                                                         Toast.LENGTH_LONG).show();
                                             }
                                         });
                } else {
                    handler.post(new Runnable() {
                        public void
                        run() {
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }
    private void renderWeather(JSONObject json){
        try {
            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            String textInfo = "Место: " + json.getString("name") + "\n" + details.getString("description").toUpperCase() + "\nТемпература минимальная: " + json.getJSONObject("main").getString("temp_min").toUpperCase() + "\nТемпература максимальная: " + json.getJSONObject("main").getString("temp_max").toUpperCase();
            //((TextView)findViewById(R.id.weather)).setText(details.getString("description").toUpperCase());
            ((TextView)findViewById(R.id.weather)).setText(textInfo);
        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSONdata");
        }
    }
}