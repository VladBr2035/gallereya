package ru.example.mygallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class BigPicture extends AppCompatActivity {
    Button toMainBtn;
    Button backBtn;
    Button nextBtn;
    ImageView imageView;
    ArrayList<String> paths;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_picture);

        imageView = findViewById(R.id.picture);
        toMainBtn = findViewById(R.id.toMainBtn);
        backBtn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);

        Bundle arguments = getIntent().getExtras();
//        String path = arguments.get("picturePath").toString();
        paths = (ArrayList<String>) arguments.get("picturesPath");
        position = (int) arguments.get("position");
        Bitmap bitmap = BitmapFactory.decodeFile(paths.get(position));
        imageView.setImageBitmap(bitmap);

        toMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0){
                    position = position - 1;
                    String path = paths.get(position);
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < paths.size()){
                    position = position + 1;
                    String path = paths.get(position);
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
    }
}