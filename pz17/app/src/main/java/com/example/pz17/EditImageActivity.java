package com.example.pz17;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditImageActivity extends AppCompatActivity {

    Bitmap selectedImage;
    ImageView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);

        Init();
    }
    private void Init(){
        photoView = findViewById(R.id.imageView);
        selectedImage = MainActivity.selectedImage;
        photoView.setImageBitmap(selectedImage);
    }
    public void Rotate_90(View view) {
        Matrix matrix = new Matrix();
        matrix.setRotate((float) 90);
        selectedImage = Bitmap.createBitmap(selectedImage, 0, 0, selectedImage.getWidth(), selectedImage.getHeight(), matrix, false);
        photoView.setImageBitmap(selectedImage);
    }

    public void Reflect(View view) {
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        selectedImage = Bitmap.createBitmap(selectedImage, 0, 0, selectedImage.getWidth(), selectedImage.getHeight(), matrix, true);
        photoView.setImageBitmap(selectedImage);
    }
    public void BlackAndWhite(View view) {
        Bitmap bwBitmap = selectedImage.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bwBitmap);
        Paint paint = new Paint();

        // Матрица для черно-белого
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0); // 0 = полностью черно-белое

        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.drawBitmap(selectedImage, 0, 0, paint);

        selectedImage = bwBitmap;
        photoView.setImageBitmap(selectedImage);
    }
    public void ResetImage(View view) {
        selectedImage = MainActivity.selectedImage.copy(Bitmap.Config.ARGB_8888, true);
        photoView.setImageBitmap(selectedImage);
    }
}