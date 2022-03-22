package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

class GalleryOne extends Activity {

    Gallery mGallery;
    TextView mTextView;
    ImageAdapter mImageAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        // Текстовое поле, в которое выводится номер выделенного элемента
        mTextView = (TextView) findViewById(R.id.selected);

        mGallery = (Gallery) findViewById(R.id.gallery);

        // Устанавливаем адаптер
        mImageAdapter = new ImageAdapter(this);
        mGallery.setAdapter(mImageAdapter);

        // Выделяем элемент по середине
        mGallery.setSelection(mImageAdapter.getCount() / 2);

        // Устанавливаем действия, которые будут выполнены при выделении элемента
        mGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                mTextView.setText(String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                mTextView.setText("");
            }
        });
    }

    // Класс адаптера
    public class ImageAdapter extends BaseAdapter {
        int mGalleryItemBackground;
        private Context mContext;

        // Массив изображений
        private int[] mImageIds = {
                R.drawable.s01, R.drawable.s02, R.drawable.s03, R.drawable.s04,
                R.drawable.s05, R.drawable.s06, R.drawable.s07, R.drawable.s08,
                R.drawable.s09, R.drawable.s10};

        public ImageAdapter(Context c) {
            mContext = c;
            TypedArray attr = mContext.obtainStyledAttributes(R.styleable.MyGallery);
            mGalleryItemBackground = attr.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            attr.recycle();
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mImageIds[position]);
            // Позиционирование по центру
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            // Размер по содержимому
            imageView.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setBackgroundResource(mGalleryItemBackground);
            return imageView;
        }
    }
}