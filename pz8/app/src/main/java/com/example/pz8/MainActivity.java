package com.example.pz8;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //    return insets;
        //});

    }

    public void onClick(View v) {
        EditText nameText = findViewById(R.id.name);
        EditText companyText = findViewById(R.id.company);
        EditText ageText = findViewById(R.id.age);
        EditText positionText = findViewById(R.id.position);

        String toastText = "";
        if (nameText.getText().toString().isEmpty())
            toastText += "Поле Имя обязательно к заполнению!\n";
        if (companyText.getText().toString().isEmpty())
            toastText += "Поле Организация обязательно к заполнению!\n";
        try {
            if (Integer.parseInt(ageText.getText().toString()) != 0)
                toastText += "Поле Возраст обязательно к заполнению!\n";
            else toastText += "Поле Возраст не должно быть равно 0!\n";
        }
        catch (Exception exception){
            toastText += "Поле Возраст должно быть числом!\n";
        }
        if (positionText.getText().toString().isEmpty())
            toastText += "Поле Должность обязательно к заполнению!\n";
        if (!toastText.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Заполните данные")
                    .setMessage(toastText)
                    .setPositiveButton(android.R.string.ok, null);
            builder.create().show();
        } else {
            String name = nameText.getText().toString();
            String company = companyText.getText().toString();
            int age = Integer.parseInt(ageText.getText().toString());
            String position = positionText.getText().toString();

            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("company", company);
            intent.putExtra("age", age);
            intent.putExtra("position", position);
            startActivity(intent);
        }


    }
}