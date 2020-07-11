package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class profile_description extends AppCompatActivity {

    Button go_back;
    Button go_edit;
    ImageView image;
    TextView text_DISPLAY_NAME;
    TextView text_COMPANY;
    TextView text_NUMBER;
    TextView text_ADDRESS;
    TextView text_URL;
    TextView text_NOTE;
    TextView text_IS_PRIMARY;


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
        Bundle bundle = getIntent().getExtras();

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

        /*
        image = findViewById(R.id.contact_profile_image);
        String path = context.getString(R.string.photoroot)+"/"+fldr+"/"+introphoto;
        InputStream is = getAssets().open(path);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        imageView.setImageBitmap(bitmap);
        is.close();
        image.setImageResource(loaded_text_PHOTO_URI);
        */
        text_DISPLAY_NAME = findViewById(R.id.contact_full_name);
        text_DISPLAY_NAME.setText(loaded_text_DISPLAY_NAME);
        text_COMPANY = findViewById(R.id.);
        text_COMPANY.setText(loaded_text_COMPANY);
        text_NUMBER = findViewById(R.id.);
        text_NUMBER.setText(loaded_text_NUMBER);
        text_ADDRESS = findViewById(R.id.);
        text_ADDRESS.setText(loaded_text_ADDRESS);
        text_URL = findViewById(R.id.);
        text_URL.setText(loaded_text_URL);
        text_NOTE = findViewById(R.id.);
        text_NOTE.setText(loaded_text_NOTE);
        text_IS_PRIMARY = findViewById(R.id.);
        text_IS_PRIMARY.setText(loaded_text_IS_PRIMARY);

        all_infos_on_single_line = findViewById(R.id.contact_full_name);
        all_infos_on_single_line.setText(loaded_string);

    }

}