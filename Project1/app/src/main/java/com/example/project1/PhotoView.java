package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class PhotoView extends LinearLayout {

    ImageView imageView;

    public PhotoView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.photo, this, true);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }
}
