package com.example.pz8;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        TextView textView = new TextView(this);
        textView.setTextSize(26);
        textView.setPadding(16,16,16,16);

        Bundle arguments = getIntent().getExtras();

        if (arguments != null){
            String name = arguments.get("name").toString();
            String company = arguments.getString("company");
            int age = arguments.getInt("age");
            String position = arguments.getString("position");
            textView.setText("Имя: "+ name + "\nОрганизация: " + company + "\nВозраст: " + age + "\nДолжность: " + position);
        }
        setContentView(textView);
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //    return insets;
        //});
    }
}