package com.example.project1;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowMetrics;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class photoFragment extends Fragment {

    ImageView imageView;
    ConstraintLayout fullPhoto;
    Button button;
    GridView gridView;
    PhotoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_photo, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridView);
        fullPhoto = (ConstraintLayout) rootView.findViewById(R.id.fullPhoto);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        button = (Button) rootView.findViewById(R.id.backButton);

        adapter = new PhotoAdapter();

        adapter.addItem(R.drawable.animal1);
        adapter.addItem(R.drawable.animal2);
        adapter.addItem(R.drawable.animal3);
        adapter.addItem(R.drawable.animal4);
        adapter.addItem(R.drawable.animal5);
        adapter.addItem(R.drawable.animal6);
        adapter.addItem(R.drawable.animal7);
        adapter.addItem(R.drawable.animal8);
        adapter.addItem(R.drawable.animal20);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int resId = (int) adapter.getItem(i);
                //PhotoView selectView = new PhotoView(getActivity().getApplicationContext());
                //selectView.setImage(resId);

                imageView.setImageResource(resId);

                fullPhoto.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.INVISIBLE);
                //constraintLayout.bringToFront();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullPhoto.setVisibility(View.INVISIBLE);
                gridView.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }

    class PhotoAdapter extends BaseAdapter {
        ArrayList<Integer> items = new ArrayList<Integer>();

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            PhotoView view = new PhotoView(getActivity().getApplicationContext());
            view.setImage((Integer) getItem(position));

            // 화면 크기에 맞게 이미지를 출력하기 위해 디스플레이의 사이즈를 구함
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            // 구한 사이즈를 view에 적용
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(size.x/3, size.x/3);
            view.setLayoutParams(params);

            return view;
        }

        public void addItem(int resId) {
            items.add(resId);
        }
    }
}


