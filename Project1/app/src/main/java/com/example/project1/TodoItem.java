package com.example.project1;

public class TodoItem {
    // int index;
    String _title;
    String _date;
    Boolean _done;

    public String getTitle() {
        return _title;
    }

    public String getDate() {
        return _date;
    }

    public Boolean isCheck() {
        return _done;
    }

    public void changeCheck() {
        _done = !_done;
    }

    public TodoItem(String title, String date, Boolean check) {
        _title = title;
        _date = date;
        _done = check;
    }
}
