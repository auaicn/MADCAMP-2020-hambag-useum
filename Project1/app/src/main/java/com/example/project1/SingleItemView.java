package com.example.project1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SingleItemView extends RelativeLayout {

    TextView contact_name;

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
        inflater.inflate(R.layout.contact_item,this,true);

        // item 이라고 해서, 하나의 뷰를 xml로 정의할 수 있지만,
        // 연락처 탭에서 필요한건 이름만으로 구성하였다. 전화번호는 onclick 후 세부사항에서 확인 할 수 있다.
        contact_name = (TextView) findViewById(R.id.contact_item);

    }

    public TextView getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name_) {
        contact_name.setText(contact_name_);
    }

}
