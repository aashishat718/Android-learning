package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView contactName;
    private TextView phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        contactName = findViewById(R.id.name_id);
        phoneNumber = findViewById(R.id.phoneNumber_id);

        String name = "Name : "+ getIntent().getStringExtra("name");
        String pn = "Phone : "+ getIntent().getStringExtra("phone");

        contactName.setText(name);
        phoneNumber.setText(pn);
    }
}
