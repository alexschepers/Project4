package edu.uga.cs.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class StartQuizActivity extends AppCompatActivity {
    //private Question
    private TextView mScoreView;
    private TextView mQuestionView;
    private RadioButton mButtonChoice1;
    private RadioButton mButtonChoice2;
    private RadioButton mButtonChoice3;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        //mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.quizQuestion);
        mButtonChoice1 = (RadioButton)findViewById(R.id.option1);
        mButtonChoice2 = (RadioButton)findViewById(R.id.option2);
        mButtonChoice3 = (RadioButton)findViewById(R.id.option3);

        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mButtonChoice1.getText() == mAnswer){
                    mScore += 1;
                }
            }
        });
    }

}