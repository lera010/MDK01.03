package com.example.pz18;

import static com.example.pz18.ConnectFetch.getIconUrl;
import static com.example.pz18.StaticWeatherAnalyze.getCityField;
import static com.example.pz18.StaticWeatherAnalyze.getDetailsField;
import static com.example.pz18.StaticWeatherAnalyze.getLastUpdateTime;
import static com.example.pz18.StaticWeatherAnalyze.getTemperatureField;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.text.DateFormat;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ConnectFetch(this, "Orenburg", new
                ConnectFetch.OnConnectionCompleteListener()
                {@Override
                public void onSuccess(JSONObject response)
                {renderWeather(response);
                }
                    @Override
                    public void onFail(String message) {
                        Toast.makeText(MainActivity.this,
                                message,
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
    private void renderWeather(JSONObject json){
        try {
            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            String textInfo = "Температура минимальная: " + json.getJSONObject("main").getString("temp_min").toUpperCase() + "\nТемпература максимальная: " + json.getJSONObject("main").getString("temp_max").toUpperCase();
            //((TextView)findViewById(R.id.weather)).setText(details.getString("description").toUpperCase());
            ((TextView)findViewById(R.id.weather)).setText(textInfo);

            Glide
                    .with(this)
                    .load(getIconUrl(json))
                    .into((ImageView)findViewById(R.id.weather_icon));
            ((TextView)findViewById(R.id.city_field)).setText(getCityField(json));
            ((TextView)findViewById(R.id.updated_field)).setText(getLastUpdateTime(json));
            ((TextView)findViewById(R.id.details_field)).setText(getDetailsField(json));
            ((TextView)findViewById(R.id.current_temperature_field)).setText(getTemperatureField(json));

        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSONdata");
        }
    }
}