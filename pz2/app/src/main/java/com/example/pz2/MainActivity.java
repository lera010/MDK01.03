package com.example.pz2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tvName, tvAge, tvSpec;
    private EditText edName, edAge, edSpec;
    private Button bEnter;
    LinearLayout enterLay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvName = findViewById(R.id.textView);
        tvAge = findViewById(R.id.textView2);
        tvSpec = findViewById(R.id.textView3);

        edName = findViewById(R.id.et_enter_name);
        edAge = findViewById(R.id.et_enter_age);
        edSpec = findViewById(R.id.et_enter_spec);

        bEnter = findViewById(R.id.b_enter);

        enterLay = findViewById(R.id.linearLayoutEdit);

        bEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvName.setText("Имя: " + edName.getText());
                tvAge.setText("Возраст: " + edAge.getText());
                tvSpec.setText("Специальность: " + edSpec.getText());

                enterLay.setVisibility(View.GONE);
            }
        });
    }
}