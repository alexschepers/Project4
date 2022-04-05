package edu.uga.cs.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

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
            // get the TableLayout view
            //TableLayout tableLayout = findViewById(R.id.table_main);

            // set up margins for each TextView in the table layout
            android.widget.TableRow.LayoutParams layoutParams =
                    new TableRow.LayoutParams( TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT );
            layoutParams.setMargins(20, 0, 20, 0);

            // read the CSV data
            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
            String[] nextRow;
            while( ( nextRow = reader.readNext() ) != null ) {

                // nextRow[] is an array of values from the line

                // create the next table row for the layout
                TableRow tableRow = new TableRow( getBaseContext() );
                for( int i = 0; i < nextRow.length; i++ ) {

                    // create a new TextView and set its text
                    TextView textView = new TextView( getBaseContext() );
                    // for all columns exept the SCHOOL, align right
                    if( i != 1 )
                        textView.setGravity(Gravity.RIGHT);
                    textView.setText( nextRow[i] );

                    // add the new TextView to the table row in the table supplying the
                    // layout parameters
                    tableRow.addView( textView, layoutParams );
                }

                // add the next row to the table layout
                tableLayout.addView( tableRow );
            }
        } catch (Exception e) {
            Log.e( TAG, e.toString() );
        }

        List<String> countryNames = new ArrayList<String>();

//
//        while(true) {
//            try {
//                if (!(reader.readNext() != null)) break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (CsvValidationException e) {
//                e.printStackTrace();
//            }
//
//        }

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