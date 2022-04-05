package edu.uga.cs.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    /**
     * Button variables.
     */
    private Button startQuizButton;
    private Button viewResultsButton;
    Country countryObjectArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStream in_s = getAssets().open("country_continent.csv");
            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
            String[] nextRow;
            int i = 0;
            countryObjectArray = new Country[196];
            while( ( nextRow = reader.readNext() ) != null ) {
                Log.i("TAG", nextRow[0] + " " + nextRow[1]);
                String countryName = nextRow[0];
                String continentName = nextRow[1];
                Country newCountry = new Country(countryName, continentName);
                countryObjectArray[i] = newCountry;
                i++;
            }
        } catch (Exception e) {
            Log.e( TAG, e.toString() );
        }

        Random randomNum = new Random();
        int upper = 196;

        Question[] questionArray = new Question [6];
        int randomNumArray[] = new int[6];
        for (int i = 0; i < randomNumArray.length; i++) {
            randomNumArray[i] = randomNum.nextInt(upper);
            Question newQuestion = new Question(countryObjectArray[randomNumArray[i]]);
            Log.i("TAG", newQuestion.toString() + " " + newQuestion.getCorrectAnswer());
            Log.i("TAG", newQuestion.getWrongAnswerOne());
            Log.i("TAG", newQuestion.getWrongAnswerTwo());
            questionArray[i] = newQuestion;
        }

        Quiz quizToUse = new Quiz (questionArray);


        /**
         * Adds functionality to the buttons.
         */
        startQuizButton  = findViewById(R.id.startQuiz);
        startQuizButton.setOnClickListener( new startButtonClickListener());

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

            /**
             * Creating a new intent to start the new activity for the button.
             */
            Intent intent = new Intent( view.getContext(), StartQuizActivity.class );
            startActivity( intent );
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