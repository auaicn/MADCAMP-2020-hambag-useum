package com.example.project1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingleItemView extends LinearLayout {

    TextView text1;
    TextView text2;
    TextView text3;
    ImageView imageview;

    public SingleItemView(Context context) {
        super(context);
        init(context);
    }

    public SingleItemView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    public void init(Context context){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.contact_layout,this,true);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        imageview = (ImageView) findViewById(R.id.profile);

    }

    public void setText1(String name) {
        text1.setText(name);
    }

    public void setText2(String mobile) {
        text2.setText(mobile);
    }

    public void setText3(int age){
        text3.setText(String.valueOf(age));
    }

    public void setImage(int resId){
        imageview.setImageResource(resId);
    }

}
