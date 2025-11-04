package com.example.pz14;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.transition.Visibility;

public class MainActivity extends AppCompatActivity {

    User user = new User("undefined", 0);
    final static String userVariableKey = "USER_VARIABLE";
    ImageView image;
    Boolean created = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.imageView);
        if (savedInstanceState == null)
            image.setVisibility(INVISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putSerializable(userVariableKey, user);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        user = (User) savedInstanceState.getSerializable(userVariableKey);
        TextView dataView = findViewById(R.id.dataView);
        dataView.setText("Name: " + user.getName() + " Age: " + user.getAge());
    }

    public void saveData(View view) {

        EditText nameBox = findViewById(R.id.nameBox);
        EditText yearBox = findViewById(R.id.yearBox);
        String name = nameBox.getText().toString();
        int age = 0;
        try {
            age = Integer.parseInt(yearBox.getText().toString());
        } catch (NumberFormatException ex) {}
        user = new User(name, age);
        image.setVisibility(INVISIBLE);
    }

    public void getData(View view) {

        TextView dataView = findViewById(R.id.dataView);
        dataView.setText("Name: " + user.getName() + " Age: " + user.getAge());
        image.setVisibility(VISIBLE);
    }
}