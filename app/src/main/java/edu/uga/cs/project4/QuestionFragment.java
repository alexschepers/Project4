package edu.uga.cs.project4;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class QuestionFragment extends Fragment {

    private static final int NUM_PAGES = 6;

    public TextView mQuestionView;
    public RadioButton mButtonChoice1;
    public RadioButton mButtonChoice2;
    public RadioButton mButtonChoice3;
    public RadioGroup radioGroup;

    public String question = "";
    public String correct = "";
    public String correctAnswer = "";
    public String wrongAnswerOne = "";
    public String wrongAnswerTwo = "";
    public String userChoice = "";

    public int mScore = 0;
    public int mQuestionNumber = 0;

    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(int position) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt("questionNum", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        mQuestionView = (TextView) rootView.findViewById(R.id.quizQuestion);
        mButtonChoice1 = (RadioButton) rootView.findViewById(R.id.option1);
        mButtonChoice2 = (RadioButton) rootView.findViewById(R.id.option2);
        mButtonChoice3 = (RadioButton) rootView.findViewById(R.id.option3);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radiogroup);

       if (getArguments() != null) {
           question = getArguments().getString("question");
           correct = getArguments().getString("correct");
           wrongAnswerOne = getArguments().getString("wrongAnswerOne");
           wrongAnswerTwo = getArguments().getString("wrongAnswerTwo");

           mQuestionView.setText(question);

           // randomly assigning answers to buttons
           Random rand = new Random();
           int upperbound = 100;
           int randomInt = rand.nextInt(upperbound);

           if (randomInt % 3 == 5) {
               mButtonChoice1.setText(correct);
               mButtonChoice2.setText(wrongAnswerOne);
               mButtonChoice3.setText(wrongAnswerTwo);
               correctAnswer = "first";
           } else if (randomInt % 2 == 0) {
               mButtonChoice1.setText(wrongAnswerTwo);
               mButtonChoice2.setText(wrongAnswerOne);
               mButtonChoice3.setText(correct);
               correctAnswer = "third";
           } else {
               mButtonChoice1.setText(wrongAnswerOne);
               mButtonChoice2.setText(correct);
               mButtonChoice3.setText(wrongAnswerTwo);
               correctAnswer = "second";
           }
       }

       //Log.i("onCreateView: " , "1");

       return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Log.i("onViewCreated", "2");

        super.onViewCreated(view, savedInstanceState);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int correctId = radioGroup.getCheckedRadioButtonId();

                if (correctId == R.id.option1) {
                    userChoice = "first";
                }
                if (correctId == R.id.option2) {
                    userChoice = "second";
                }
                if (correctId == R.id.option3) {
                    userChoice = "third";
                }
                Log.i("XXXcorrectAnswer", correctAnswer);
                Log.i("XXXuserChoice", userChoice);
                updateScore(correctAnswer, userChoice);
            }

        });
    }

    private void updateScore(String correctAnswer, String choice) {
        if (correctAnswer.equals(choice) && correctAnswer != "") {
            mScore += 1;
            ((StartQuizActivity)getActivity()).updateScore();
        }
        int testing = ((StartQuizActivity)getActivity()).getScore();
        Log.i("global_variable_score", String.valueOf(testing));
    }

    private void clearSelection(){
        if(radioGroup != null) {
            radioGroup.clearCheck();
            //Log.i("clear ", "cleared");
        } else {
            //Log.i("clear ", "not cleared");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}

class MyAdapter extends FragmentStateAdapter {

    private final int NUM_PAGES;
    Quiz quiz = new Quiz();

    ArrayList<String> questionArray;
    ArrayList<String> correctAnswerArray;
    ArrayList<String> wrongAnswerOneArray;
    ArrayList<String> wrongAnswerTwoArray;
    Country[] countryObjectArray;

    @SuppressLint("LongLogTag")


    public MyAdapter(FragmentActivity fm, int size) {
        super(fm);
        this.NUM_PAGES = size;

        try {
            InputStream in_s = fm.getAssets().open("country_continent.csv");
            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
            String[] nextRow;
            int i = 0;
            countryObjectArray = new Country[196];
            while( ( nextRow = reader.readNext() ) != null ) {
                String countryName = nextRow[0];
                //Log.i("while loop country", countryName);
                String continentName = nextRow[1];
                //Log.i("while loop continent", continentName);
                Country newCountry = new Country(countryName, continentName);
                countryObjectArray[i] = newCountry;
                i++;
            }
            in_s.close();
        } catch (Exception e) {
            Log.e( "question fragment adapter my adapter constructor", e.toString() );
        }

        Random randomNum = new Random();
        int upper = 196;

        questionArray = new ArrayList<>();
        correctAnswerArray = new ArrayList<>();
        wrongAnswerOneArray = new ArrayList<>();
        wrongAnswerTwoArray = new ArrayList<>();
        int randomNumArray[] = new int[6];
        for (int i = 0; i < randomNumArray.length; i++) {
            randomNumArray[i] = randomNum.nextInt(upper);
            Question newQuestion = new Question(countryObjectArray[randomNumArray[i]]);

            questionArray.add(newQuestion.toString());
            correctAnswerArray.add(newQuestion.getCorrectAnswer());
            wrongAnswerOneArray.add(newQuestion.getWrongAnswerOne());
            wrongAnswerTwoArray.add(newQuestion.getWrongAnswerTwo());
        }

    }

    @Override
    public Fragment createFragment(int position) {
        //Log.i("createFragment", "new fragment at " + position + " is being created.");

        QuestionFragment questionFragment = QuestionFragment.newInstance(position + 1);
        Bundle bundle = new Bundle();
        bundle.putString("question", questionArray.get(position));
        bundle.putString("correct", correctAnswerArray.get(position));
        bundle.putString("wrongAnswerOne", wrongAnswerOneArray.get(position));
        bundle.putString("wrongAnswerTwo", wrongAnswerTwoArray.get(position));
        bundle.putInt("score", (int) quiz.getScore());

        questionFragment.setArguments(bundle);

        return questionFragment;
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }


}