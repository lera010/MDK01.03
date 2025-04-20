package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button prev;
    private Button next;
    private ImageView img;
    private TextView textView;
    private int[] photos = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
    private int index = 0;
    private int count = photos.length-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();
     }
    private void init(){
        prev = (Button)findViewById(R.id.prev);
        prev.setOnClickListener(this);
        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(this);
        img = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
            if (v.getId() == R.id.prev){
                if (index == 0) index = count;
                else index--;
            }
            else if (v.getId() == R.id.next){
                if(index == count) index = 0;
                else index++;
            }

        img.setImageResource(photos[index]);
        textView.setText(index+1 + "/" + photos.length);
    }
}