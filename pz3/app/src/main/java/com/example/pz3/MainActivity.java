package com.example.pz3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private TextView textView;
    private Button yellowButton;
    private Button greenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        constraintLayout = findViewById(R.id.root_layout);
        textView = findViewById(R.id.textView);

        yellowButton = findViewById(R.id.button_yellow);
        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.yellow);//установка текста
                constraintLayout.setBackgroundColor(getColor(R.color.yellowColor));//установка цвета
                yellowButton.setBackgroundColor(getColor(R.color.yellowColor));//изменение цвета кнопки
            }
        });

        greenButton = findViewById(R.id.button_green);
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.green);//установка текста
                constraintLayout.setBackgroundColor(getColor(R.color.greenColor));//установка цвета
                greenButton.setBackgroundColor(getColor(R.color.greenColor));//изменение цвета кнопки
            }
        });
    }

    public void onClickRedButton(View view) {
        constraintLayout.setBackgroundColor(getColor(R.color.redColor));//установка цвета
        textView.setText(R.string.red);//установка текста
        Button redButton = findViewById(R.id.button_red);//объявление и инициалия кнопки, чтобы обратиться и поменять ее цвет
        redButton.setBackgroundColor(getColor(R.color.redColor));//изменение цвета кнопки
    }
}