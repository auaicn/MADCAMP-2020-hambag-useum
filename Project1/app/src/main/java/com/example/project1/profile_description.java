package com.example.project1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class profile_description extends AppCompatActivity {

    Button go_back;
    Button go_edit;
    ImageView profile_image;
    TextView text_DISPLAY_NAME;
    TextView text_COMPANY;
    TextView text_NUMBER;
    TextView text_ADDRESS;
    TextView text_URL;
    TextView text_NOTE;
    TextView text_ToggleButton_Below;
    ImageView image_Starred;

    private String contact_id;
    private String whether_starred;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_description);

        go_back = findViewById(R.id.button_contacts_back);
        go_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String loaded_string;
        if (bundle != null) {
            Log.d("auaicn", "bundle not empty");
            if ((loaded_string = bundle.getString("profile_in_detail")) == null) {
                Log.d("auaicn", "information loading from main activity to description activity failed");
                finish();
            }
        }else
            loaded_string = null;

        Log.d("auaicn", "bundle empty");
        Log.d("auaicn", loaded_string);

        String[] parsed_string = loaded_string.split("__");

        text_DISPLAY_NAME = findViewById(R.id.contact_full_name);
        text_COMPANY = findViewById(R.id.contact_company);
        text_NUMBER = findViewById(R.id.contact_phone_number);
        text_ADDRESS = findViewById(R.id.contact_email_address);
        text_URL = findViewById(R.id.contact_homepage);
        text_NOTE = findViewById(R.id.contact_notes);

        text_DISPLAY_NAME.setText(parsed_string[1]);
        text_COMPANY.setText(parsed_string[2]);
        text_NUMBER.setText(parsed_string[3]);
        text_ADDRESS.setText(parsed_string[4]);
        text_URL.setText(parsed_string[5]);
        text_NOTE.setText(parsed_string[6]);

        // Local variable setting for toggling and resolver operation (ex. Insert, Delete, Update)
        contact_id = parsed_string[8];
        whether_starred = parsed_string[7];

        // Image decoded here.
        ImageDecoder.Source profile_image_source = null;
        if (parsed_string[0] != null){
            Log.d("image setting","has image");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                profile_image_source = ImageDecoder.createSource(getContentResolver(), Uri.parse(parsed_string[0]));
                profile_image.setImageURI(Uri.parse(parsed_string[0]));
            }
        }
        else
            Log.d("image setting","no profile image");


        // Whether Starred, star-Image right of profile image differs (filled, not filled)
        image_Starred = findViewById(R.id.contact_starred);
        text_ToggleButton_Below = findViewById(R.id.contact_toggle_star_button);

        if(parsed_string[7].equals("1") == true){
            image_Starred.setImageResource(R.drawable.starred_true);
            text_ToggleButton_Below.setText("+ unStar This Contact");
        }
        else {
            image_Starred.setImageResource(R.drawable.starred_false);
            text_ToggleButton_Below.setText("+ Star This Contact");
        }
        // [Setting] Two Ways to toggle Favorite
        Button Star_Top_image = findViewById(R.id.star_button);
        Button Star_bottom = findViewById(R.id.contact_toggle_star_button);
        Star_bottom.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                ContentValues update_contents = new ContentValues();

                if(whether_starred.equals("1"))
                    update_contents.put("starred", "0");
                else
                    update_contents.put("starred", "1");

                Uri target_uri = ContactsContract.Contacts.CONTENT_URI;

                // need contact ID
                getContentResolver().update(
                        target_uri,
                        update_contents,
                        ContactsContract.Data._ID + " = ?",
                        new String[]{contact_id});

                // refresh manually.
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });

        /*
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // 여기 position 가지고, 가져올 수 있는 것 같다.
                SingleItem item = (SingleItem) adapter.getItem(position);
                String string_item = item.toString();
                Log.d("to_stringed item",string_item);

                // Toast.makeText(getActivity(),"선택 : " + item.getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), profile_description.class);
                Log.d("auaicn","Starting activity succeed");

                if (intent.putExtra("profile_in_detail",string_item) == null)
                    Log.d("auaicn","intent putting extra failed");
                else
                    Log.d("auaicn","intent putting extra succeed");
                startActivity(intent);
                Log.d("auaicn","Starting activity succeed");
            }
        });

        //text_IS_PRIMARY.setOnClickListener(new OnClickLis);
        */
    }

}