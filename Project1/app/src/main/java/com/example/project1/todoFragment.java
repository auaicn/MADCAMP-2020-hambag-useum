package com.example.project1;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class todoFragment extends Fragment {

    ListView todoView;
    List<TodoItem> todo;
    TodoAdapter adapter;
    //SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_todo, container, false);

        todoView = (ListView) rootView.findViewById(R.id.todoView);
        todo = new ArrayList<>();
        adapter = new TodoAdapter();
        //db = SQLiteDatabase.openOrCreateDatabase("todoList", null);

        TodoItem todo1 = new TodoItem("Shallow", "2020-04-20", false);
        TodoItem todo2 = new TodoItem("Music to my eyes", "2020-04-21", false);
        TodoItem todo3 = new TodoItem("Heal Me", "2020-04-22", true);

        todo.add(todo1);
        todo.add(todo2);
        todo.add(todo3);

        adapter.setData(todo);

        todoView.setAdapter(adapter);

        todoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("hamApp", "onItemClick");
                ((TodoAdapter)todoView.getAdapter()).changeItemCheck(i);
                todoView.setAdapter(adapter);
            }
        });

        return rootView;
    }

    final class TodoAdapter extends BaseAdapter {
        List<TodoItem> _todo = new ArrayList<>();

        void setData(List<TodoItem> todo) {
            if(this._todo.size() > 0){
                this._todo.clear();
            }
            this._todo = todo;
        }

        void changeItemCheck(int i) {
            _todo.get(i).changeCheck();
        }

        @Override
        public int getCount() {
            return _todo.size();
        }

        @Override
        public Object getItem(int i) {
            return _todo.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            Log.d("hamApp", "getView");

            TodoItemView itemView;
            TodoItem todoItem = todo.get(i);

            if(view == null) {
                itemView = new TodoItemView(getContext());
            } else {
                itemView = (TodoItemView) view;
            }

            itemView.setContents(todoItem.getTitle(), todoItem.getDate(), todoItem.isCheck());

            return itemView;
        }
    }

}