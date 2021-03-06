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
public class QuizResultsRecyclerAdapter extends RecyclerView.Adapter<QuizResultsRecyclerAdapter.QuizHolder> {

    public static final String DEBUG_TAG = "CountryRecyclerAdapter";

    private List<Quiz> quizList;

    public QuizResultsRecyclerAdapter(List<Quiz> quizList ) {
        this.quizList = quizList;
    }

    // The adapter must have a ViewHolder class to "hold" one item to show.
    class QuizHolder extends RecyclerView.ViewHolder {

        TextView quizNum;
        TextView score;
        TextView date;

        public QuizHolder(View itemView ) {
            super(itemView);

            quizNum = (TextView) itemView.findViewById(R.id.quizNum);
            score = (TextView) itemView.findViewById( R.id.score );
            date = (TextView) itemView.findViewById( R.id.date );
        }
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        // We need to make sure that all CardViews have the same, full width, allowed by the parent view.
        // This is a bit tricky, and we must provide the parent reference (the second param of inflate)
        // and false as the third parameter (don't attach to root).
        // Consequently, the parent view's (the RecyclerView) width will be used (match_parent).
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.quiz, parent, false );
        return new QuizHolder( view );
    }

    // This method fills in the values of a holder to show a JobLead.
    // The position parameter indicates the position on the list of jobs list.
    @Override
    public void onBindViewHolder( QuizHolder holder, int position ) {
        Quiz quiz = quizList.get( position );

        holder.quizNum.setText("Quiz Number: " + String.valueOf(quiz.getId()));
        holder.score.setText("Score: " + String.valueOf(quiz.getScore()));
        holder.date.setText("Date: " + String.valueOf(quiz.getTime()));

        Log.d( DEBUG_TAG, "onBindViewHolder: " + quiz );

    }

    @Override
    public int getItemCount() {
        if( quizList != null )
            return quizList.size();
        else
            return 0;
    }
}
