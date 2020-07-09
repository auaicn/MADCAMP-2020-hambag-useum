package com.example.project1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class photoFragment extends Fragment {

    ListView listView;
    PhotoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_photo, container, false);

        listView = (ListView) rootView.findViewById(R.id.photolistView);
        adapter = new PhotoAdapter();

        adapter.addItem(R.drawable.animal1);
        adapter.addItem(R.drawable.animal2);
        adapter.addItem(R.drawable.animal3);
        adapter.addItem(R.drawable.animal4);
        adapter.addItem(R.drawable.animal5);
        listView.setAdapter(adapter);

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

            return view;
        }

        public void addItem(int resId) {
            items.add(resId);
        }
    }
}


