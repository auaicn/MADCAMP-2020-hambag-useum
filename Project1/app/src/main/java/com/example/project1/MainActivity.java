package com.example.project1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private phoneFragment phoneFragment;
    private photoFragment photoFragment;
    private todoFragment todoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        photoFragment = new photoFragment();
        phoneFragment = new phoneFragment();
        todoFragment = new todoFragment();
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(phoneFragment, "Phone");
        viewPagerAdapter.addFragment(photoFragment, "Photo");
        viewPagerAdapter.addFragment(todoFragment, "To-Do");
        viewPager.setAdapter(viewPagerAdapter);

        todoFragment.todoDB = init_database("todoList.db");
        init_tables(todoFragment.todoDB, "CONTACT");

        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int screenHeight = constraintLayout.getRootView().getMeasuredHeight();
                Log.d("hamApp", Integer.toString(screenHeight));
            }
        });
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position){
            return fragmentTitle.get(position);
        }
    }

    public SQLiteDatabase init_database(String databaseFileName) {
        SQLiteDatabase db = null;
        File file = new File(getFilesDir(), databaseFileName);

        Log.d("hamApp", "PATH: " + file.toString());
        //System.out.println("PATH: " + file.toString());

        try {
            db = SQLiteDatabase.openOrCreateDatabase(file, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (db == null) { System.out.println("DB creation failed. " + file.getAbsolutePath()); }

        return db;
    }

    private void init_tables(SQLiteDatabase sqliteDB, String tableName) {
        if(sqliteDB == null) return;

        String sqlCreateTbl = "CREATE TABLE IF NOT EXISTS "+ tableName + " (" +
                "TITLE "       + "TEXT NOT NULL," +
                "DATE "     + "TEXT," +
                "DONE "   + "INTEGER" + ")";
        System.out.println(sqlCreateTbl);
        sqliteDB.execSQL(sqlCreateTbl);
    }
}