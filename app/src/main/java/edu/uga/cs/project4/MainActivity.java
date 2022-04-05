package edu.uga.cs.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

        try {
            InputStream in_s = getAssets().open("country_continent.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> countryNames = new ArrayList<String>();
        CSVReader reader = new CSVReader(new InputStreamReader());

        while( reader.readNext() != null) {

        }

        /*
        Random rand= new Random();

        int upperbound = 196;
        int quizCountryNum[] = {};
        String countryQuestion[] ={};
        for(int i = 0; i < 6; i++){
            quizCountryNum[i] = rand.nextInt(upperbound);
            //read from data table and place country with id that matches the randomly generated number into the countryQuestion array
            //when quiz starts, pull country from array and insert into question
            countryQuestion[i] = String.valueOf(countryList.get(quizCountryNum[i]));
        }
        */

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
            Intent intent = new Intent( view.getContext(),StartQuiz.class );
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