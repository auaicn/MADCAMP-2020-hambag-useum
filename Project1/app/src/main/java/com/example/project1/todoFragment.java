package com.example.project1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class todoFragment extends Fragment {

    RecyclerView todoView;
    RecyclerView.LayoutManager layoutManager;

    TodoAdapter adapter;

    SQLiteDatabase todoDB;

    EditText editTextTitle;
    ImageButton addButton;
    Button dateButton;

    DatePickerDialog dialog;

    boolean isEditPageOpen = false;
    Animation translateLeftAnim;
    Animation translateRightAnim;
    LinearLayout editPage;
    Button editButton;
    Button cancelButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("hamApp todoFragment", "onCreateView");

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_todo, container, false);

        todoView = (RecyclerView) rootView.findViewById(R.id.todoView);
        //todoView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        todoView.setLayoutManager(layoutManager);

        adapter = new TodoAdapter(getContext(), todoDB);
        todoView.setAdapter(adapter);

        Calendar calendar = new GregorianCalendar();
        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH); // 실제 월은 +1
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                // month는 1~12가 아닌 0~11로 되어있다.
                Toast.makeText(getContext(), i + "/" + (i1+1) + "/" + i2, Toast.LENGTH_SHORT).show();
                dateButton.setText(i + "/" + (i1+1) + "/" + i2);
            }
        }, curYear, curMonth, curDay);

        editTextTitle = rootView.findViewById(R.id.editTextTitle);
        addButton = rootView.findViewById(R.id.addButton);
        dateButton = rootView.findViewById(R.id.dateButton);
        dateButton.setText(curYear+ "/" + (curMonth+1) + "/" + curDay);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                //DialogFragment dialogFragment = new DatePickerDialogTheme();
                //dialogFragment.show(getFragmentManager(), "Theme");
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString();
                String date = dateButton.getText().toString();

                int num = numberOfItem();
                TodoItemView newItem = new TodoItemView(num, title, date, 0);

                insertItem(num, newItem);
                adapter.notifyDataSetChanged();

                editTextTitle.setText("");
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void insertItem(int num, TodoItemView newItem) {
        //Log.d("hamApp todoFragment", "insertItem");
        if(todoDB == null) return;

        //deleteItem(newItem);

        String sqlInsert = "INSERT INTO CONTACT " +
                "(NUM, TITLE, DATE, EMOTION) VALUES (" +
                Integer.toString(num) + ", " +
                "'" + newItem.getTitle() + "', " +
                "'" + newItem.getDate() + "', " + "0)";

        //Log.d("hamApp todoFragment", sqlInsert);
        todoDB.execSQL(sqlInsert);

    }

    public int numberOfItem() {
        //Log.d("hamApp todoFragment", "numberOfItem");
        if(todoDB == null) return 0;

        String sqlQueryTbl = "SELECT * FROM CONTACT";
        Cursor cursor = todoDB.rawQuery(sqlQueryTbl, null);

        return cursor.getCount();
    }

    public void deleteItem(int position) {
        if(todoDB == null) return;

        String sqlInsert = "DELETE FROM CONTACT " +
                "WHERE NUM = " + Integer.toString(position);
        todoDB.execSQL(sqlInsert);
    }
    /*
    public static class DatePickerDialogTheme extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            dateButton.setText(i + "/" + (i1+1) + "/" + i2);
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            Calendar calendar = new GregorianCalendar();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_DEVICE_DEFAULT_DARK,this,year,month,day);

            return datepickerdialog;
        }
    }*/
}