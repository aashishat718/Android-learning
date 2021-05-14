package com.example.sprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_ID = "message_prefs";
    private EditText editText;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText_id);
        textView=findViewById(R.id.textView_id);
        button=findViewById(R.id.button_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message= editText.getText().toString().trim();

                SharedPreferences sharedPreferences= getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("message",message);

                editor.apply();   // saving to disk
            }
        });

        // get data from shared preferences
        SharedPreferences getShareData = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
        String value=getShareData.getString("message","Nothing yet");
        textView.setText(value);

    }
}
