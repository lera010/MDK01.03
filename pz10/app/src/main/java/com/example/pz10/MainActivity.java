package com.example.pz10;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> users = new ArrayList();
    ArrayList<String> selectedUsers = new ArrayList();
    ArrayAdapter<String> adapter;
    ListView userslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Collections.addAll(users, "Иванов", "Петров", "Сидоров", "Кузнeцов");
        userslist = findViewById(R.id.usersList);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, users);
        userslist.setAdapter(adapter);
        userslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                String user = adapter.getItem(position);
                if (userslist.isItemChecked(position))
                    selectedUsers.add(user);
                else
                    selectedUsers.remove(user);

            }
        });
    }

    public void add(View view) {

        EditText userName = findViewById(R.id.userName);
        EditText userSurname = findViewById(R.id.userSurname);
        EditText userBirth = findViewById(R.id.userBirth);
        String user = userName.getText().toString();
        String userSur = userSurname.getText().toString();
        String userBir = userBirth.getText().toString();
        String res = "";
        String err = "";
        if (userSur.isEmpty()) {
            err += "Фамилия";
        } else res += userSur;
        if (user.isEmpty()) {
            if (err.isEmpty()) err += "Имя";
            else err += ", Имя";
        }
        else res += user;
        if (err.isEmpty()) {
            adapter.add(userSur + " " + user + " " + userBir) ;
            userName.setText("");
            userSurname.setText("");
            userBirth.setText("");
            adapter.notifyDataSetChanged();
        }
        else {
            Toast toast = new Toast(this);
            toast.setGravity(Gravity.BOTTOM, 0,0);
            toast.setText("Заполните поля: "+ err);
            toast.show();
        }
    }

    public void remove(View view) {
        for (int i = 0; i < selectedUsers.size(); i++){
            adapter.remove(selectedUsers.get(i));
        }

            userslist.clearChoices();
            selectedUsers.clear();
            adapter.notifyDataSetChanged();

    }
}
