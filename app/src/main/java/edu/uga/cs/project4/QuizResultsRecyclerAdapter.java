package edu.uga.cs.project4;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * This is an adapter class for the RecyclerView to show all job leads.
 */
public class QuizResultsRecyclerAdapter extends RecyclerView.Adapter<QuizResultsRecyclerAdapter.CountryHolder> {

    public static final String DEBUG_TAG = "CountryRecyclerAdapter";

    private List<Quiz> quizList;

    public QuizResultsRecyclerAdapter(List<Quiz> quizList ) {
        this.quizList = quizList;
    }

    // The adapter must have a ViewHolder class to "hold" one item to show.
    class CountryHolder extends RecyclerView.ViewHolder {

        TextView quizNum;
        TextView date;

        public CountryHolder(View itemView ) {
            super(itemView);

            quizNum = (TextView) itemView.findViewById( R.id.quizNum );
            date = (TextView) itemView.findViewById( R.id.date );
        }
    }

    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        // We need to make sure that all CardViews have the same, full width, allowed by the parent view.
        // This is a bit tricky, and we must provide the parent reference (the second param of inflate)
        // and false as the third parameter (don't attach to root).
        // Consequently, the parent view's (the RecyclerView) width will be used (match_parent).
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.activity_view_quiz_results, parent, false );
        return new CountryHolder( view );
    }

    // This method fills in the values of a holder to show a JobLead.
    // The position parameter indicates the position on the list of jobs list.
    @Override
    public void onBindViewHolder( CountryHolder holder, int position ) {
        Country country = countryList.get( position );

        Log.d( DEBUG_TAG, "onBindViewHolder: " + country );

    }

    @Override
    public int getItemCount() {
        if( countryList != null )
            return countryList.size();
        else
            return 0;
    }
}
