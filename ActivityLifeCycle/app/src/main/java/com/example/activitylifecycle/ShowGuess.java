package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowGuess extends AppCompatActivity {

    private TextView showRecievedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        showRecievedText=findViewById(R.id.recieve_guess);

        if(getIntent().getStringExtra("guess") != null){
            String value=getIntent().getStringExtra("guess");
            showRecievedText.setText(value);

            showRecievedText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.putExtra("message_back","From second activity");
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
    }
}
