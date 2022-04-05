package edu.uga.cs.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    /**
     * Button variables.
     */
    private Button startQuizButton;
    private Button viewResultsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Adds functionality to the buttons.
         */
        startQuizButton  = findViewById(R.id.startQuiz);
        //startQuizButton.setOnClickListener( new startButtonClickListener());
        viewResultsButton = findViewById(R.id.viewResults);
        //viewResultsButton.setOnClickListener( new resultsButtonClickListener());

    }

    /**
     * Implements functionality to this button.
     * Clicking the overview button starts this method.
     */
    private class startButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick( View view ) {
            Log.i(TAG, "Start quiz button has been pressed.");
            Random rand= new Random();
            int upperbound = 196;
            int quizCountryNum[] = {};
            String countryQuestion[] ={};
            for(int i = 0; i < 6; i++){
                quizCountryNum[i] = rand.nextInt(upperbound);
                //read from data table and place country with id that matches the randomly generated number into the countryQuestion array
                //when quiz starts, pull country from array and insert into question
                if() {
                    countryQuestion[i] = "";
                }
            }

            /**
             * Creating a new intent to start the new activity for the button.
             */
            //Intent intent = new Intent( view.getContext(),startQuiz.class );
           //startActivity( intent );
        }
    }

    private class resultsButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick( View view ) {
            Log.i(TAG, "View results button has been pressed.");

            /**
             * Creating a new intent to start the new activity for the button.
             */
            //Intent intent = new Intent( view.getContext(),viewQuizResultsActivity.class );
            //startActivity( intent );
        }
    }

}