package com.example.shownamenow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button myButton;
    private TextView text;
    private boolean flag;
    private EditText enterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.button);
        text = findViewById(R.id.textView);
        enterName = findViewById(R.id.editText);
        flag=false;

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=enterName.getText().toString();
                if(flag==false) {
                    text.setText(name);
                    flag = true;
                }
                else
                {
                    text.setText(null);
                    flag = false;
                }
            }
        });
    }
}
