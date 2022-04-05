package edu.uga.cs.project4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ViewQuizResultsActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "ViewQuizResultsActivity";

    private RecyclerView recyclerView;
    private QuizResultsRecyclerAdapter recyclerAdapter;

    private CountryData countryData = null;
    private List<Quiz> quizResultsList = null;
    private List<Country> countryList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quiz_results);

        recyclerView = findViewById( R.id.RecyclerView );

        // use a linear layout manager for the recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        quizResultsList = new ArrayList<Quiz>();
        recyclerAdapter = new QuizResultsRecyclerAdapter( quizResultsList );
        recyclerView.setAdapter( recyclerAdapter );

        // Create a JobLeadsData instance, since we will need to save a new JobLead to the dn.
        // Note that even though more activites may create their own instances of the JobLeadsData
        // class, we will be using a single instance of the JobLeadsDBHelper object, since
        // that class is a singleton class.
        countryData = new CountryData( this );

        // Open that database for reading of the full list of job leads.
        // Note that onResume() hasn't been called yet, so the db open in it
        // was not called yet!
        countryData.open();

        // Execute the retrieval of the job leads in an asynchronous way,
        // without blocking the main UI thread.
        new CountryDBReader().execute();

    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of countries, asynchronously.
    private class CountryDBReader extends AsyncTask<Void, List<Country>> {
        // This method will run as a background process to read from db.
        // It returns a list of retrieved JobLead objects.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onCreate callback (the job leads review activity is started).
        @Override
        protected List<Country> doInBackground( Void... params ) {
            List<Country> countryList = countryData.retrieveAllCountries();

            //Log.d( DEBUG_TAG, "JobLeadDBReaderTask: Job leads retrieved: " + jobLeadsList.size() );

            return countryList;
        }

        // This method will be automatically called by Android once the db reading
        // background process is finished.  It will then create and set an adapter to provide
        // values for the RecyclerView.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( List<Country> jList ) {
            //recyclerAdapter = new JobLeadRecyclerAdapter( jList );
            //recyclerView.setAdapter( recyclerAdapter );
            Log.d( DEBUG_TAG, "jList.size(): " + jList.size() );
            countryList.addAll(jList);
            recyclerAdapter.notifyDataSetChanged();
        }
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB writing of a job lead, asynchronously.
    public class JobLeadDBWriter extends AsyncTask<Country, Country> {

        // This method will run as a background process to write into db.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onClick listener of the Save button.
        @Override
        protected Country doInBackground( Country... jobLeads ) {
            countryData.storeCountry( jobLeads[0] );
            return jobLeads[0];
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.  Note that doInBackground returns a JobLead object.
        // That object will be passed as argument to onPostExecute.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( Country country ) {
            // Update the recycler view to include the new job lead
            countryList.add( country );
            recyclerAdapter.notifyItemInserted(countryList.size() - 1);

            Log.d( DEBUG_TAG, "Job lead saved: " + country );
        }
    }

    // this is our own callback for a DialogFragment which adds a new job lead.
//    public void onFinishNewJobLeadDialog(Country country) {
//        // add the new job lead
//        new CountryDBWriter().execute( country );
//    }

    void showDialogFragment( DialogFragment newFragment ) {
        newFragment.show( getSupportFragmentManager(), null);
    }

    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onResume()" );
        // open the database in onResume
        if( countryData != null && !countryData.isDBOpen() )
            countryData.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onPause()" );
        // close the database in onPause
        if( countryData != null )
            countryData.close();
        super.onPause();
    }
}