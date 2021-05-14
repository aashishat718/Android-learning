package com.example.makeitrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static com.example.makeitrain.R.string.money;

public class MainActivity extends AppCompatActivity {

//    private Button showMoney;
//    private Button showTag;
    private TextView moneyText;
    private int moneyCounter=0;
    private ConstraintLayout mainBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyText=findViewById(R.id.money_text);
        mainBackground=findViewById(R.id.primary_background);
//        showMoney=findViewById(R.id.make_it_rain);
//        showTag=findViewById(R.id.button_show_tag);
//        showMoney.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("MY TAG", "Make it rain:Show Money");
//            }
//        });
    }

    public void showTag(View v){

        moneyText.setText(R.string.money);
        moneyCounter=0;
        Toast.makeText(getApplicationContext(),"All money withdrawn",Toast.LENGTH_SHORT).show();
        mainBackground.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
        //moneyText.setTextColor(Color.);
        //Log.d("Show tag", "showTag");
    }

    public void makeItRain(View v)
    {
        NumberFormat moneyFormat=NumberFormat.getCurrencyInstance();
        moneyCounter+=1000;
        moneyText.setText(String.valueOf(moneyFormat.format(moneyCounter)));

        switch(moneyCounter)
        {
            case 10000:
                moneyText.setTextColor(Color.BLACK);
                Toast.makeText(getApplicationContext(), "You'r getting richer", Toast.LENGTH_SHORT).show();
                break;
            case 20000:
                moneyText.setTextColor(getResources().getColor(R.color.myColor));
                Toast.makeText(getApplicationContext(), "You'r getting richer", Toast.LENGTH_SHORT).show();
                break;
            case 30000:
                moneyText.setTextColor(Color.RED);
                mainBackground.setBackgroundColor(Color.YELLOW);
                Toast.makeText(getApplicationContext(), "You'r getting richer", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        //Log.d("MY TAG", "showMoney: money");
    }

}
