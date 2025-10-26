package com.example.pz11;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> products;
    ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = new ArrayList<Product>();
        if (products.size() == 0) {
            products.add(new Product("Лук","кг."));
            products.add(new Product("Чай", "шт."));
            products.add(new Product("Яйца", "шт."));
            products.add(new Product("Молоко", "л."));
            products.add(new Product("Cахар", "кг."));
            products.add(new Product("Картофель", "кг."));
        }

        ListView productList = findViewById(R.id.productList);
        adapter = new ProductAdapter(this, R.layout.list_item, products);
        productList.setAdapter(adapter);

    }
    public void addProduct(View view){
        EditText name = findViewById(R.id.NameProduct);
        String nameProd = name.getText().toString();
        EditText edIsm = findViewById(R.id.NameEdIsm);
        String edIsmStr = edIsm.getText().toString();
        if (nameProd.isEmpty() || edIsmStr.isEmpty()){
            Toast toast = new Toast(this);
            toast.setGravity(Gravity.BOTTOM, 0,0);
            toast.setText("Заполните поле названия продукта и поле единицы измерения");
            toast.show();
        }
        else {
            adapter.add(new Product(nameProd,edIsmStr)) ;
            name.setText("");
            edIsm.setText("");
            adapter.notifyDataSetChanged();

        }
    }
}