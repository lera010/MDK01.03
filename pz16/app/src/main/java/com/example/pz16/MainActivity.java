package com.example.pz16;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView userList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = findViewById(R.id.list);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.getReadableDatabase();

        //userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
        //String[] headers = new String[]{DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_YEAR, DatabaseHelper.COLUMN_GENDER};
        //userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
        //        userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        //userList.setAdapter(userAdapter);
        userCursor = db.rawQuery("select *, " +
                DatabaseHelper.COLUMN_NAME + " || ' - ' || " +
                DatabaseHelper.COLUMN_YEAR + " || ' - ' || " +
                DatabaseHelper.COLUMN_GENDER + " as display_text from " +
                DatabaseHelper.TABLE, null);

        String[] headers = new String[]{"display_text"};
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                userCursor, headers, new int[]{android.R.id.text1}, 0);
        userList.setAdapter(userAdapter);
    }

    public void add(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        userCursor.close();
    }
}