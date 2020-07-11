package com.example.project1;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.Checkable;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CheckableConstraintLayout extends ConstraintLayout implements Checkable {
    public CheckableConstraintLayout(Context context) {
        super(context);
    }

    @Override
    public void setChecked(boolean b) {
        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(b);
    }

    @Override
    public boolean isChecked() {
        CheckBox checkBox = findViewById(R.id.checkBox);
        return checkBox.isChecked();
    }

    @Override
    public void toggle() {
        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(!checkBox.isChecked());
    }
}
