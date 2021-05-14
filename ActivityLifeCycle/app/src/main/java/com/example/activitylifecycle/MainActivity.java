package com.example.activitylifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button showGuess;
    private EditText guessedName;
    private final int REQUEST_CODE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showGuess=findViewById(R.id.guess_button);
        guessedName=findViewById(R.id.guess_name_text);

        showGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = guessedName.getText().toString().trim();

                if (!value.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, ShowGuess.class);
                    intent.putExtra("guess", value);
                    startActivityForResult(intent,REQUEST_CODE);
                } else {
                    Toast.makeText(MainActivity.this, "Guess any name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE){
            assert data!=null;
            String value=data.getStringExtra("message_back");
            Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
        }

    }
}
