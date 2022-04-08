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

    private TextView mQuestionView;
    private RadioButton mButtonChoice1;
    private RadioButton mButtonChoice2;
    private RadioButton mButtonChoice3;

    private String question;
    private String correct;
    private String wrongAnswerOne;
    private String wrongAnswerTwo;


    private int mScore = 0;
    private int mQuestionNumber = 0;


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
        //setContentView(R.layout.activity_start_quiz);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        mQuestionView = (TextView) rootView.findViewById(R.id.quizQuestion);
        mButtonChoice1 = (RadioButton) rootView.findViewById(R.id.option1);
        mButtonChoice2 = (RadioButton) rootView.findViewById(R.id.option2);
        mButtonChoice3 = (RadioButton) rootView.findViewById(R.id.option3);

        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            question = getArguments().getString("question");
            mQuestionView.setText(question);
            Log.i("test", question);
            correct = getArguments().getString("correct");
            mButtonChoice1.setText(correct);
            Log.i("test", correct);
            wrongAnswerOne = getArguments().getString("wrongAnswerOne");
            mButtonChoice2.setText(wrongAnswerOne);
            Log.i("test", wrongAnswerOne);
            wrongAnswerTwo = getArguments().getString("wrongAnswerTwo");
            mButtonChoice3.setText(wrongAnswerTwo);
            Log.i("test", wrongAnswerTwo);
        }

        RadioGroup radioGroup = view.findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb=(RadioButton)view.findViewById(checkedId);

                if (checkedId == R.id.option1 && mButtonChoice1.getText().toString().equals(correct)) {
                    Log.i("score", "score is going up");
                    mScore++;
                }
                if (checkedId == R.id.option2 && mButtonChoice2.getText().toString().equals(correct)) {
                    Log.i("score", "score is going up");
                    mScore++;
                }
                if (checkedId == R.id.option3 && mButtonChoice3.getText().toString().equals(correct)) {
                    Log.i("score", "score is going up");
                    mScore++;
                }
            }
        });


    }

    // idea  one
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.option1:
                if (checked && mButtonChoice1.getText().equals(correct))
                    mScore += 1;
                    break;
            case R.id.option2:
                if (checked && mButtonChoice2.getText().equals(correct))
                    mScore += 1;
                    break;
            case R.id.option3:
                if (checked && mButtonChoice3.getText().equals(correct))
                    mScore += 1;
                    break;
        }
    }

}

class MyAdapter extends FragmentStateAdapter {

    private final int NUM_PAGES;

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
                String continentName = nextRow[1];
                Country newCountry = new Country(countryName, continentName);
                countryObjectArray[i] = newCountry;
                i++;
            }
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

        QuestionFragment questionFragment = QuestionFragment.newInstance(position + 1);
        Bundle bundle = new Bundle();
        bundle.putString("question", questionArray.get(position));
        Log.i("bundle", bundle.getString("question"));
        bundle.putString("correct", correctAnswerArray.get(position));
        Log.i("bundle", bundle.getString("correct"));
        bundle.putString("wrongAnswerOne", wrongAnswerOneArray.get(position));
        Log.i("bundle", bundle.getString("wrongAnswerOne"));
        bundle.putString("wrongAnswerTwo", wrongAnswerTwoArray.get(position));
        Log.i("bundle", bundle.getString("wrongAnswerTwo"));
        questionFragment.setArguments(bundle);

        return questionFragment;
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }

}