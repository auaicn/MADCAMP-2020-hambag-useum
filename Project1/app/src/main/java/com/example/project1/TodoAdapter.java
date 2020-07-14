package com.example.project1;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


final class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private SQLiteDatabase todoDB;
    Context context;

    boolean isCheckbox = true;

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        protected TextView num;
        protected TextView title;
        protected TextView date;
        protected ImageButton emotion;
        LinearLayout textField;


        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            this.num = (TextView) itemView.findViewById(R.id.num);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.emotion = (ImageButton) itemView.findViewById(R.id.emotionButton);

            emotion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Log.d("hamApp TodoAdapter", "touch checkBox: index: " + Integer.toString(position));
                    changeEmotion(getAdapterPosition());
                    switch(getEmotion(position)) {
                        case 0:
                            emotion.setImageResource(R.drawable.ic_0_no); break;
                        case 1:
                            emotion.setImageResource(R.drawable.ic_1_smile); break;
                        case 2:
                            emotion.setImageResource(R.drawable.ic_2_angry); break;
                        case 3:
                            emotion.setImageResource(R.drawable.ic_3_sad); break;
                    }
                    Log.d("hamApp TodoAdapter", "--click end--");
                }
            });

            textField = itemView.findViewById(R.id.textField);
            textField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("hamApp textfield", "onClick");
                    Intent intent = new Intent(context, TodoDetailActivity.class);
                    intent.putExtra("num", num.getText());
                    intent.putExtra("title", title.getText());
                    intent.putExtra("date", date.getText());
                    intent.putExtra("todoDB", String.valueOf(todoDB));
                    Log.d("hamApp ggg:", String.valueOf(todoDB));

                    context.startActivity(intent);
                    ((MainActivity)context).overridePendingTransition(R.anim.sliding_up, R.anim.stay);
                }
            });
        }
    }

    public TodoAdapter(Context context, SQLiteDatabase DB) {
        this.context = context;
        this.todoDB = DB;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todoitem, parent, false);
        TodoViewHolder viewHolder = new TodoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {

        if(position < 10) holder.num.setText("0" + position);
        else holder.num.setText(""+position);
        holder.title.setText(getTitle(position));
        holder.date.setText(getDate(position));
        switch(getEmotion(position)) {
            case 0:
                holder.emotion.setImageResource(R.drawable.ic_0_no); break;
            case 1:
                holder.emotion.setImageResource(R.drawable.ic_1_smile); break;
            case 2:
                holder.emotion.setImageResource(R.drawable.ic_2_angry); break;
            case 3:
                holder.emotion.setImageResource(R.drawable.ic_3_sad); break;
        }
    }

    @Override
    public int getItemCount() {
        //Log.d("hamApp TodoAdapter", "getItemCount");
        if(todoDB == null) return 0;

        String sqlQueryTbl = "SELECT * FROM CONTACT";
        Cursor cursor = todoDB.rawQuery(sqlQueryTbl, null);
        //Log.d("hamApp TodoAdapter", Integer.toString(cursor.getCount()));

        return cursor.getCount();
    }

    String getTitle(int position) {
        if(todoDB == null) return null;

        String sqlInsert = "SELECT * FROM CONTACT " +
                "WHERE NUM = " +
                Integer.toString(position);
        Cursor cursor = todoDB.rawQuery(sqlInsert, null);
        cursor.moveToNext();
        //Log.d("hamApp TodoAdapter", sqlInsert);

        return cursor.getString(1);
    }

    String getDate(int position) {
        //Log.d("hamApp TodoAdapter", "getDate");

        if(todoDB == null) return null;

        String sqlInsert = "SELECT * FROM CONTACT " +
                "WHERE NUM = " + Integer.toString(position);
        Cursor cursor = todoDB.rawQuery(sqlInsert, null);
        cursor.moveToNext();

        return cursor.getString(2);
    }

    int getEmotion(int position) {
        Log.d("hamApp TodoAdapter", "getEmotion:");

        String sqlInsert = "SELECT * FROM CONTACT " +
                "WHERE NUM = " +
                Integer.toString(position);

        Cursor cursor = todoDB.rawQuery(sqlInsert, null);
        cursor.moveToNext();

        Log.d("hamApp TodoAdapter", Integer.toString(cursor.getInt(3)));
        return cursor.getInt(3);
    }

    void changeEmotion(int position) {
        String sqlInsert = "UPDATE CONTACT " +
                "SET EMOTION = (EMOTION+1) % 4 " +
                "WHERE NUM = " +
                Integer.toString(position);

        Log.d("hamApp TodoAdapter", "changeEmotion: "+sqlInsert);
        todoDB.execSQL(sqlInsert);
    }
}