package com.example.project1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class todoFragment extends Fragment {

    ListView todoView;
    List<TodoItem> todo;
    TodoAdapter adapter;
    SQLiteDatabase todoDB;

    EditText editTextTitle;
    EditText editTextDate;
    ImageButton addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_todo, container, false);
        // final View datePickView = inflater.inflate(R.layout.date_picker, container, false);
        Log.d("hamApp", "onCreateView_todofragment");

        todoView = (ListView) rootView.findViewById(R.id.todoView);
        todo = new ArrayList<>();
        adapter = new TodoAdapter();

        adapter.setDataFromDB(todoDB);
        todoView.setAdapter(adapter);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        todoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("hamApp", "onItemClick");
                ((TodoAdapter) todoView.getAdapter()).changeItemCheck(i);
                todoView.setAdapter(adapter);
            }
        });

        editTextTitle = rootView.findViewById(R.id.editTextTitle);
        editTextDate = rootView.findViewById(R.id.editTextDate);
        addButton = rootView.findViewById(R.id.addButton);

        Log.d("hamApp", (addButton==null? "null":"not null"));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString();
                String date = editTextDate.getText().toString();

                editTextTitle.setText("");
                editTextDate.setText("");

                insertItem(title, date,false);

                adapter.setDataFromDB(todoDB);
                todoView.setAdapter(adapter);

                imm.hideSoftInputFromWindow(addButton.getWindowToken(), 0);
            }
        });

        return rootView;
    }

    final class TodoAdapter extends BaseAdapter {
        //List<TodoItem> _todo = new ArrayList<>();
        SQLiteDatabase todoDB = null;

        void setDataFromDB(SQLiteDatabase DB) {
            todoDB = DB;
        }

        void changeItemCheck(int i) {
            String sqlQueryTbl = "SELECT * FROM CONTACT";
            Cursor cursor = todoDB.rawQuery(sqlQueryTbl, null);

            for(int j=0; j<=i; j++)
                cursor.moveToNext();

            String title = cursor.getString(0);
            changeCheck(title);
        }

        @Override
        public int getCount() {
            // return _todo.size();
            if(todoDB == null) {
                Log.d("hamApp", "getCount: 0");
                return 0;
            }
            String sqlQueryTbl = "SELECT * FROM CONTACT";
            Cursor cursor = todoDB.rawQuery(sqlQueryTbl, null);

            Log.d("hamApp", String.valueOf(cursor.getCount()));
            return cursor.getCount();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            String title;
            String date;
            int isCheck;

            Log.d("hamApp", "getView");

            TodoItemView itemView;

            if(view == null) {
                itemView = new TodoItemView(getContext());
            } else {
                itemView = (TodoItemView) view;
            }

            // if(todoDB == null) return null;

            String sqlQueryTbl = "SELECT * FROM CONTACT";
            Cursor cursor = todoDB.rawQuery(sqlQueryTbl, null);

            for(int j=0; j<=i; j++)
                cursor.moveToNext();

            //if(!cursor.isAfterLast()) {
                title = cursor.getString(0);
                date = cursor.getString(1);
                isCheck = cursor.getInt(2);
                itemView.setContents(title, date, (isCheck==1)? true : false);
            //}

            return itemView;
        }
    }

    public void deleteItem(String title) {
        if(todoDB == null) return;

        String sqlInsert = "DELETE FROM CONTACT " +
                "WHERE TITLE = " +
                "'" + title + "'";
        todoDB.execSQL(sqlInsert);
    }
    public void insertItem(String title, String date, Boolean done) {
        if(todoDB == null) return;

        deleteItem(title);

        String sqlInsert = "INSERT INTO CONTACT " +
                "(TITLE, DATE, DONE) VALUES (" +
                "'" + title + "', " +
                "'" + date + "', " +
                (done ? "1" : "0") + ")";

        System.out.println(sqlInsert);
        todoDB.execSQL(sqlInsert);
    }

    public void changeCheck(String title) {
        if(todoDB == null) return;

        String sqlInsert = "UPDATE CONTACT " +
                "SET DONE = DONE*(-1)+1 " +
                "WHERE TITLE = " + "'" + title + "'";

        System.out.println(sqlInsert);
        todoDB.execSQL(sqlInsert);
    }
}