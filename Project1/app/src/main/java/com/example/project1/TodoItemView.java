package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class TodoItemView extends ConstraintLayout {

    //int index;
    TextView title;
    TextView date;
    CheckBox checkBox;

    public TodoItemView(Context context) {
        super(context);

        // layout을 메모리에 객체화
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.todoitem, this, true);

        // layout의 객체 참조
        title = (TextView) findViewById(R.id.title);
        date = (TextView) findViewById(R.id.date);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

    }

    public void setContents(String title, String date, Boolean check) {
        this.title.setText(title);
        this.date.setText(date);
        this.checkBox.setChecked(check);
        updateButton(check);
    }

    public void updateButton(Boolean isCheck) {
        if(isCheck) {
            checkBox.setButtonDrawable(R.drawable.ic_check);
        } else {
            checkBox.setButtonDrawable(R.drawable.ic_uncheck);
        }
    }
}
