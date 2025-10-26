package com.example.pz9;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ListView myListView;
    private int[] names = {R.string.ic_name_1, R.string.ic_name_2, R.string.ic_name_3, R.string.ic_name_4, R.string.ic_name_5, R.string.ic_name_6};
    private int[] icons = {R.drawable.ic_batman, R.drawable.ic_geisha_japanese, R.drawable.ic_girl, R.drawable.ic_nun_sister, R.drawable.ic_woman, R.drawable.ic_harli};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.lv);
        myListView.setAdapter(new MyBaseAdapter());
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {// Cobupaem ddHHble
            View view = View.inflate(MainActivity.this, R.layout.list_item,
            null); // B List_item dsa ueHTuguka
            TextView mTextView = (TextView) view.findViewById(R.id.tv_list);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            mTextView.setText(names[position]);
            imageView.setBackgroundResource(icons[position]);
            return view;
        }
    }
}