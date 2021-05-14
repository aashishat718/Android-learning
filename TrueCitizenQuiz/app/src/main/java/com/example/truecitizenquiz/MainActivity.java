package com.example.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton previousButton;
    private TextView questionText;

    private int questionCount=0;

    private Question[] questionBank = new Question[]{
            new Question(R.string.quiz_question,true),
            new Question(R.string.ques1,true),
            new Question(R.string.ques2,false),
            new Question(R.string.ques3,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        previousButton=findViewById(R.id.previous_button);
        questionText=findViewById(R.id.question_text);

        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.next_button:
                questionCount=(questionCount+1)%questionBank.length;
                updateQuestion();
                break;
            case R.id.previous_button:
                questionCount--;
                if(questionCount<0) questionCount=questionBank.length-1;
                updateQuestion();
                break;
        }

    }

    private void checkAnswer(boolean userChoice){
        boolean correctAnswer=questionBank[questionCount].isAnswerTrue();
        int makeToastId=0;
        if(correctAnswer==userChoice)
            makeToastId=R.string.correct_answer;
        else
            makeToastId=R.string.wrong_answer;

        Toast.makeText(MainActivity.this,makeToastId,Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion()
    {
        Log.d("Counter","Question Number"+questionCount);
        questionText.setText(questionBank[questionCount].getAnswerResId());
    }
}
