package edu.uga.cs.project4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class StartQuizActivity extends AppCompatActivity {

    int score = 0;
    ViewPager2 viewPager;
    Quiz quiz = new Quiz();

    private QuizData quizData;
    Button b;
    TextView swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        viewPager = findViewById(R.id.pager);
        MyAdapter myPagerAdapter = new MyAdapter(this, 6);
        viewPager.setAdapter(myPagerAdapter);

        quizData = new QuizData(this);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.i("position", String.valueOf(position));
                //Log.i("viewpager", String.valueOf(viewPager.getAdapter().getItemCount()));
                int check = position + 1;
                if (check == viewPager.getAdapter().getItemCount()) {
                    //Log.i("onPageSelected", "going into if statement");
                    b = findViewById(R.id.button);
                    b.setVisibility(View.VISIBLE);
                    b.setOnClickListener( new ButtonClickListener());

                    swipe = findViewById(R.id.swipeHelp);
                    swipe.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            quiz.setScore(score);
            Log.i("quiz final score", String.valueOf(quiz.getScore()));

            // Store this new job lead in the database asynchronously,
            // without blocking the UI thread.
            new QuizDBWriter().execute( quiz);
        }
    }

    public int getScore() {
        return score;
    }

    public void updateScore() {
        score += 1;
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB writing of a job lead, asynchronously.
    public class QuizDBWriter extends AsyncTask<Quiz, Quiz> {

        // This method will run as a background process to write into db.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onClick listener of the Save button.
        @Override
        protected Quiz doInBackground( Quiz... quizzes ) {
            quizData.storeQuiz( quizzes[0] );
            return quizzes[0];
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.  Note that doInBackground returns a JobLead object.
        // That object will be passed as argument to onPostExecute.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( Quiz jobLead ) {

        }

    }



}