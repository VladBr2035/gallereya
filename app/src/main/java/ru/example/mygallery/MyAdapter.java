package ru.example.mygallery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    /**Спискок изображений*/
    private ArrayList<Cell> galleryList;
    private Context context;

    // Предоставляет подходящий конструктор (зависит от типа набора данных)
    public MyAdapter(Context context, ArrayList<Cell> galleryList) {
        this.context = context;
        this.galleryList = galleryList;
    }

    // Создание новых представлений (вызывается менеджером компоновки)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        // создание a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell, parent, false);
        return new ViewHolder(view);
    }

    //Заменить содержимое представления (вызывается менеджером компоновки)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        // - получить элемент из вашего набора данных в этой позиции
        // - заменить содержимое представления этим элементом
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setImageFromPath(galleryList.get(position).getPath(), viewHolder.img);
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO что-то может происходить, если кликнуть на изображение
                Toast.makeText(context, "" + galleryList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, BigPicture.class);
                ArrayList<String> paths = new ArrayList<>();
                for (int i = 0; i < galleryList.size(); i++){
                    paths.add(galleryList.get(i).getPath());
                }
                intent.putExtra("picturesPath", paths);
                intent.putExtra("position", position);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    // Указывает ссылку на представления для каждого элемента данных
    //Сложные элементы данных могут нуждаться в более чем одном представлении для каждого элемента, и
    //вы предоставляете доступ ко всем представлениям для элемента данных в держателе представления
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // каждый элемент данных в этом случае является просто строкой
        public ImageView img;

        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
        }
    }


    // Вернуть размер вашего набора данных (вызывается менеджером компоновки)
    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    private void setImageFromPath(String path, ImageView image) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            Bitmap myBitmap = ImageHelper.decodeSampleBitmapFromPath(imgFile.getAbsolutePath(), 200, 200);
            image.setImageBitmap(myBitmap);
        }
    }
}
